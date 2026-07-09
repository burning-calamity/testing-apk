param(
    [switch]$NoPause
)

$ErrorActionPreference = 'Stop'
$ExitCode = 0

function Pause-BeforeExit {
    if (-not $NoPause) {
        Write-Host ''
        Write-Host 'Press any key to close this window . . .'
        try {
            $null = $Host.UI.RawUI.ReadKey('NoEcho,IncludeKeyDown')
        }
        catch {
            cmd /c pause | Out-Null
        }
    }
}

try {
    $RepoRoot = Resolve-Path (Join-Path $PSScriptRoot '..\..')
    Set-Location $RepoRoot

    Write-Host "Building no-device desktop EXE from $RepoRoot"

    if (-not (Get-Command py -ErrorAction SilentlyContinue)) {
        throw 'Python launcher `py` was not found. Install Python 3.10+ for Windows first, then run this script again.'
    }

    py -m venv .venv
    if ($LASTEXITCODE -ne 0) { throw "Failed to create the Python virtual environment. Exit code: $LASTEXITCODE" }

    & .\.venv\Scripts\python.exe -m pip install --upgrade pip
    if ($LASTEXITCODE -ne 0) { throw "Failed to upgrade pip. Exit code: $LASTEXITCODE" }

    & .\.venv\Scripts\python.exe -m pip install -r tools\windows_launcher\requirements.txt
    if ($LASTEXITCODE -ne 0) { throw "Failed to install packaging requirements. Exit code: $LASTEXITCODE" }

    & .\.venv\Scripts\python.exe -m PyInstaller packaging\windows_desktop\yuyuyui_desktop.spec --clean --noconfirm
    if ($LASTEXITCODE -ne 0) { throw "PyInstaller failed. Exit code: $LASTEXITCODE" }

    Write-Host "Built $RepoRoot\dist\YuyuyuiDesktopShell.exe"
}
catch {
    $ExitCode = 1
    Write-Host ''
    Write-Host 'Build failed:' -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
    Write-Host ''
    Write-Host 'Tip: run this script from PowerShell to copy the full error output:' -ForegroundColor Yellow
    Write-Host '.\packaging\windows_desktop\build_desktop_shell.ps1'
}
finally {
    Pause-BeforeExit
}

exit $ExitCode
