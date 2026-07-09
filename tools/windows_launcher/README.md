# Windows EXE launcher bootstrap

This directory starts the APK-to-EXE effort with an emulator-backed Windows launcher. The current repository is a decompiled Android Unity APK tree, not a Unity project with Windows player settings, so it cannot be converted directly into a native Windows game executable from these files alone.

The launcher provides a practical first `.exe` target:

1. Start an Android emulator or connect an Android device with USB debugging enabled.
2. Install Android Platform Tools so `adb` is available on `PATH`.
3. Build the launcher on Windows:

   ```powershell
   py -m venv .venv
   .\.venv\Scripts\Activate.ps1
   pip install -r tools\windows_launcher\requirements.txt
   pyinstaller packaging\windows\yuyuyui_launcher.spec
   ```

4. Run the generated executable:

   ```powershell
   dist\YuyuyuiLauncher.exe --apk path\to\game.apk
   ```

If the package is already installed in the emulator, omit `--apk` to launch it directly.

## Native EXE path

A true native Windows `.exe` requires the original Unity project or a reconstructed Unity project with Windows-compatible plugins and assets. The Android-only native libraries in `resources/lib/arm64-v8a` and `resources/lib/armeabi-v7a` cannot be loaded by Windows, so they must be replaced by Windows builds before a native Unity player can be produced.
