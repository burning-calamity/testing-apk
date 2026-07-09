"""Windows launcher bootstrap for the decompiled Android Unity game.

This is not a native Unity Windows port yet. It is the first executable
packaging layer: a small Windows app that can install and start the Android
build on an attached emulator/device through adb. Once a real Windows Unity
export exists, this entry point can be replaced with a native player launcher
while keeping the packaging workflow.
"""
from __future__ import annotations

import argparse
import shutil
import subprocess
import sys
from pathlib import Path

PACKAGE_NAME = "jp.co.altplus.yuyuyui"
MAIN_ACTIVITY = "com.google.firebase.MessagingUnityPlayerActivity"


def run_adb(args: list[str]) -> subprocess.CompletedProcess[str]:
    adb = shutil.which("adb")
    if adb is None:
        raise RuntimeError(
            "adb was not found on PATH. Install Android Platform Tools and start "
            "an Android emulator before running this launcher."
        )

    return subprocess.run(
        [adb, *args],
        check=False,
        text=True,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
    )


def require_success(result: subprocess.CompletedProcess[str], action: str) -> None:
    if result.returncode != 0:
        raise RuntimeError(f"Failed to {action}:\n{result.stdout.strip()}")


def install_apk(apk_path: Path) -> None:
    if not apk_path.is_file():
        raise FileNotFoundError(f"APK not found: {apk_path}")
    result = run_adb(["install", "-r", str(apk_path)])
    require_success(result, f"install {apk_path}")
    print(result.stdout.strip())


def launch_app() -> None:
    result = run_adb([
        "shell",
        "am",
        "start",
        "-n",
        f"{PACKAGE_NAME}/{MAIN_ACTIVITY}",
    ])
    require_success(result, f"launch {PACKAGE_NAME}")
    print(result.stdout.strip())


def parse_args(argv: list[str]) -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Install and launch the APK build through an Android emulator/device."
    )
    parser.add_argument(
        "--apk",
        type=Path,
        help="Optional APK to install before launching. If omitted, only launches the package.",
    )
    parser.add_argument(
        "--install-only",
        action="store_true",
        help="Install the APK and exit without launching it.",
    )
    return parser.parse_args(argv)


def main(argv: list[str] | None = None) -> int:
    args = parse_args(sys.argv[1:] if argv is None else argv)
    try:
        if args.apk is not None:
            install_apk(args.apk)
        if not args.install_only:
            launch_app()
    except Exception as exc:  # keep the packaged exe user-friendly
        print(f"ERROR: {exc}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
