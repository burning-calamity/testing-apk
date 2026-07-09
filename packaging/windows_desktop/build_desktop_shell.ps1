$ErrorActionPreference = 'Stop'

$RepoRoot = Resolve-Path (Join-Path $PSScriptRoot '..\..')
Set-Location $RepoRoot

if (-not (Get-Command py -ErrorAction SilentlyContinue)) {
    throw 'Python launcher `py` was not found. Install Python 3.10+ for Windows first.'
}

py -m venv .venv
& .\.venv\Scripts\python.exe -m pip install --upgrade pip
& .\.venv\Scripts\python.exe -m pip install -r tools\windows_launcher\requirements.txt
& .\.venv\Scripts\python.exe -m PyInstaller packaging\windows_desktop\yuyuyui_desktop.spec --clean --noconfirm

Write-Host "Built $RepoRoot\dist\YuyuyuiDesktopShell.exe"
