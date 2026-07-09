# How to turn this APK project into an EXE

There are two different meanings of “turn this into an EXE”:

## Option 1: Build a no-device Windows EXE now

This creates `YuyuyuiDesktopShell.exe`, a real Windows executable that does not use `adb`, an emulator, or a phone. It is a desktop compatibility shell around the recovered APK contents, not the complete playable game.

### Build the no-device EXE

From the repository root in PowerShell:

```powershell
.\packaging\windows_desktop\build_desktop_shell.ps1
```

If you are double-clicking from File Explorer, use this wrapper instead because it always pauses before closing:

```text
packaging\windows_desktop\build_desktop_shell.cmd
```

That creates:

```text
dist\YuyuyuiDesktopShell.exe
```

If the script fails when double-clicked, it now stays open and shows the error. To run it from an already-open terminal without the final pause, use:

```powershell
.\packaging\windows_desktop\build_desktop_shell.ps1 -NoPause
```

## Option 2: Build an emulator EXE launcher

This repository is a decompiled Android Unity APK, so another practical Windows `.exe` is a launcher that starts the Android build in an Android emulator.

### Requirements

On a Windows PC, install:

1. Python 3.10 or newer.
2. Android Studio or Android Platform Tools.
3. An Android emulator with Google Play support, or a USB-connected Android device with USB debugging enabled.
4. A rebuilt or original APK file for this game.

### Build the launcher EXE

From the repository root in PowerShell:

```powershell
.\packaging\windows\build_launcher.ps1
```

If you are double-clicking from File Explorer, use this wrapper instead because it always pauses before closing:

```text
packaging\windows\build_launcher.cmd
```

That creates:

```text
dist\YuyuyuiLauncher.exe
```

To run it from an already-open terminal without the final pause, use:

```powershell
.\packaging\windows\build_launcher.ps1 -NoPause
```

### Run it

Start your emulator first, then run:

```powershell
.\dist\YuyuyuiLauncher.exe --apk C:\path\to\game.apk
```

After the APK is installed once, you can launch it without reinstalling:

```powershell
.\dist\YuyuyuiLauncher.exe
```

## Option 3: Make a real native Windows game EXE

A native Windows game executable cannot be produced directly from this decompiled APK tree alone. The files under `resources/lib/arm64-v8a` and `resources/lib/armeabi-v7a` are Android `.so` libraries. Windows cannot load those libraries.

To make a real native Unity Windows `.exe`, you need one of these:

1. The original Unity project, then build it with Unity's Windows target.
2. A reconstructed Unity project containing compatible assets, scripts, scenes, and Windows builds of every native plugin.

The high-level native port process is:

1. Recover or reconstruct the Unity project.
2. Replace Android-only plugins with Windows `.dll` equivalents.
3. Remove or rewrite Android-specific Java, billing, push notification, and Play Games integrations.
4. Open the project in the matching Unity editor version.
5. Select **File > Build Settings > PC, Mac & Linux Standalone > Windows**.
6. Build the Windows player to generate the native `.exe`.

## What is in this repo now

- `tools/windows_desktop_stub/yuyuyui_desktop.py`: no-device desktop shell source.
- `packaging/windows_desktop/yuyuyui_desktop.spec`: no-device desktop shell PyInstaller build spec.
- `packaging/windows_desktop/build_desktop_shell.ps1`: one-command no-device EXE build script.
- `tools/windows_launcher/yuyuyui_launcher.py`: emulator launcher source.
- `packaging/windows/yuyuyui_launcher.spec`: emulator launcher PyInstaller build spec.
- `packaging/windows/build_launcher.ps1`: one-command emulator launcher build script.
- `tools/windows_launcher/README.md`: launcher-specific notes.
