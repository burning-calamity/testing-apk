@echo off
setlocal
cd /d "%~dp0..\.."
echo Building emulator launcher EXE. This window will stay open when the build finishes.
echo.
powershell.exe -NoProfile -ExecutionPolicy Bypass -File "%~dp0build_launcher.ps1" -NoPause
set BUILD_EXIT=%ERRORLEVEL%
echo.
if not "%BUILD_EXIT%"=="0" (
  echo Build failed with exit code %BUILD_EXIT%.
) else (
  echo Build completed successfully.
)
echo.
pause
exit /b %BUILD_EXIT%
