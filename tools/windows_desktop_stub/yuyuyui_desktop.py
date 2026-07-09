"""Standalone Windows desktop compatibility shell for the decompiled APK tree.

This intentionally avoids adb, emulators, and mobile devices. It does not claim to
be a full native port of the Unity/IL2CPP game; instead it is a Windows EXE entry
point that can be packaged today and can inspect the recovered APK contents while
native Windows replacements are developed.
"""
from __future__ import annotations

import os
import sys
import tkinter as tk
from pathlib import Path
from tkinter import filedialog, messagebox, ttk
from xml.etree import ElementTree

PACKAGE_FALLBACK = "jp.co.altplus.yuyuyui"
VERSION_FALLBACK = "unknown"
ANDROID_NS = "{http://schemas.android.com/apk/res/android}"


def candidate_roots() -> list[Path]:
    """Return likely repository roots, preferring real folders over bundles.

    PyInstaller one-file apps unpack bundled data into a temporary _MEIPASS
    directory. That directory may contain a copied manifest for app metadata, but
    it is not the recovered APK tree and should not mask the real repo when the
    EXE is launched from there.
    """
    cwd = Path.cwd()
    roots = [cwd, cwd.parent]
    if getattr(sys, "frozen", False):
        exe_dir = Path(sys.executable).resolve().parent
        roots.extend([exe_dir, exe_dir.parent])
    source_root = Path(__file__).resolve().parents[2]
    roots.extend([source_root, source_root.parent])
    bundle = getattr(sys, "_MEIPASS", None)
    if bundle:
        roots.append(Path(bundle).resolve())

    unique_roots: list[Path] = []
    for root in roots:
        resolved = root.resolve()
        if resolved not in unique_roots:
            unique_roots.append(resolved)
    return unique_roots


def has_recovered_content(root: Path) -> bool:
    return any(
        (root / relative_path).exists()
        for relative_path in (
            Path("resources/assets/bin/Data"),
            Path("resources/lib"),
            Path("sources"),
        )
    )


def find_repo_root() -> Path | None:
    for root in candidate_roots():
        if (root / "resources" / "AndroidManifest.xml").is_file() and has_recovered_content(root):
            return root
    return None


def find_manifest_root() -> Path | None:
    repo_root = find_repo_root()
    if repo_root is not None:
        return repo_root
    for root in candidate_roots():
        if (root / "resources" / "AndroidManifest.xml").is_file():
            return root
    return None


def parse_manifest(root: Path | None) -> tuple[str, str]:
    if root is None:
        return PACKAGE_FALLBACK, VERSION_FALLBACK
    manifest = root / "resources" / "AndroidManifest.xml"
    try:
        tree = ElementTree.parse(manifest)
        element = tree.getroot()
        package = element.attrib.get("package", PACKAGE_FALLBACK)
        version = element.attrib.get(f"{ANDROID_NS}versionName", VERSION_FALLBACK)
        return package, version
    except Exception:
        return PACKAGE_FALLBACK, VERSION_FALLBACK


def count_files(path: Path) -> int:
    if not path.exists():
        return 0
    return sum(1 for item in path.rglob("*") if item.is_file())


def summarize_tree(root: Path | None) -> dict[str, str]:
    if root is None:
        return {
            "Project root": "not found",
            "Unity data files": "0",
            "Android libraries": "0",
            "Java source files": "0",
        }
    return {
        "Project root": str(root),
        "Unity data files": str(count_files(root / "resources" / "assets" / "bin" / "Data")),
        "Android libraries": str(count_files(root / "resources" / "lib")),
        "Java source files": str(count_files(root / "sources")),
    }


class DesktopShell(tk.Tk):
    def __init__(self) -> None:
        super().__init__()
        self.title("Yuyuyui Windows Compatibility Shell")
        self.geometry("780x520")
        self.minsize(680, 440)
        self.repo_root = find_repo_root()
        self.package_name, self.version = parse_manifest(find_manifest_root())
        self._build_ui()

    def _build_ui(self) -> None:
        root = ttk.Frame(self, padding=18)
        root.pack(fill=tk.BOTH, expand=True)

        heading = ttk.Label(
            root,
            text="Yuyuyui Windows Compatibility Shell",
            font=("Segoe UI", 18, "bold"),
        )
        heading.pack(anchor=tk.W)

        subheading = ttk.Label(
            root,
            text=(
                f"Package: {self.package_name}   Version: {self.version}\n"
                "This EXE runs on Windows without an emulator or phone. It is a starting shell, "
                "not a completed native Unity port."
            ),
            wraplength=720,
        )
        subheading.pack(anchor=tk.W, pady=(8, 16))

        summary = ttk.LabelFrame(root, text="Recovered APK contents")
        summary.pack(fill=tk.X)
        for row, (label, value) in enumerate(summarize_tree(self.repo_root).items()):
            ttk.Label(summary, text=label + ":", width=18).grid(row=row, column=0, sticky=tk.W, padx=8, pady=4)
            ttk.Label(summary, text=value).grid(row=row, column=1, sticky=tk.W, padx=8, pady=4)

        explanation = ttk.LabelFrame(root, text="Why this is not the full game yet")
        explanation.pack(fill=tk.BOTH, expand=True, pady=(16, 0))
        text = tk.Text(explanation, wrap=tk.WORD, height=9)
        text.pack(fill=tk.BOTH, expand=True, padx=8, pady=8)
        text.insert(
            tk.END,
            "The APK contains Android ARM .so libraries and Android Java glue code. "
            "Windows cannot load those Android binaries directly. This shell proves the "
            "Windows EXE packaging path without relying on a mobile device. To turn it "
            "into the playable native PC game, replace Android-only pieces with Windows "
            "Unity/IL2CPP builds and wire the recovered assets into a Windows Unity player.\n\n"
            "Next practical steps:\n"
            "1. Reconstruct or recover the matching Unity project.\n"
            "2. Replace Android plugins with Windows DLLs or desktop implementations.\n"
            "3. Build the project for PC, Mac & Linux Standalone > Windows in Unity.\n",
        )
        text.configure(state=tk.DISABLED)

        actions = ttk.Frame(root)
        actions.pack(fill=tk.X, pady=(14, 0))
        ttk.Button(actions, text="Choose repo folder", command=self.choose_repo).pack(side=tk.LEFT)
        ttk.Button(actions, text="Open Unity data folder", command=self.open_data_folder).pack(side=tk.LEFT, padx=8)
        ttk.Button(actions, text="Close", command=self.destroy).pack(side=tk.RIGHT)

    def choose_repo(self) -> None:
        selected = filedialog.askdirectory(title="Select repository folder")
        if not selected:
            return
        candidate = Path(selected)
        if not (candidate / "resources" / "AndroidManifest.xml").is_file():
            messagebox.showerror("Invalid folder", "That folder does not contain resources/AndroidManifest.xml")
            return
        self.repo_root = candidate
        messagebox.showinfo("Folder selected", "Restart the shell to refresh the displayed summary.")

    def open_data_folder(self) -> None:
        if self.repo_root is None:
            messagebox.showerror("Missing data", "Could not find the repository resources folder.")
            return
        data_dir = self.repo_root / "resources" / "assets" / "bin" / "Data"
        if not data_dir.exists():
            messagebox.showerror("Missing data", f"Could not find {data_dir}")
            return
        os.startfile(data_dir) if sys.platform == "win32" else messagebox.showinfo("Data folder", str(data_dir))


def main() -> int:
    app = DesktopShell()
    app.mainloop()
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
