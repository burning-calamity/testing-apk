# How to turn this APK project into an EXE

There are two different meanings of “turn this into an EXE”:

## Option 1: Build an EXE launcher now

This repository is a decompiled Android Unity APK, so the fastest working Windows `.exe` is a launcher that starts the Android build in an Android emulator. This has already been scaffolded.

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

That creates:

```text
dist\YuyuyuiLauncher.exe
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

## Option 2: Make a real native Windows game EXE

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

- `tools/windows_launcher/yuyuyui_launcher.py`: Python launcher source.
- `packaging/windows/yuyuyui_launcher.spec`: PyInstaller build spec.
- `packaging/windows/build_launcher.ps1`: one-command Windows build script.
- `tools/windows_launcher/README.md`: launcher-specific notes.
