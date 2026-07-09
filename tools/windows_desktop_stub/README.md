# Windows desktop compatibility shell

This is the no-device EXE path. It builds a Windows program that runs without `adb`, an emulator, or a phone.

It is not the complete playable native game. The APK contains Android ARM native libraries, so a playable Windows port still requires a reconstructed Unity project and Windows plugin replacements. This shell gives the project a Windows executable entrypoint while that porting work happens.

Build it on Windows from the repo root:

```powershell
.\packaging\windows_desktop\build_desktop_shell.ps1
```

Output:

```text
dist\YuyuyuiDesktopShell.exe
```

If double-clicking the PowerShell script closes too quickly on your machine, double-click `packaging\windows_desktop\build_desktop_shell.cmd` instead. The CMD wrapper runs the same build and always pauses before closing.
