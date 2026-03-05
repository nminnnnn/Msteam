# Bien dich va chay CLIENT (trong Cursor / PowerShell)
# Thu muc hien tai: __DA_CLIENT\__DA_CLIENT

$ErrorActionPreference = "Stop"
$root = $PSScriptRoot
$lib = Join-Path $root "lib"
$out = Join-Path $root "out"
$src = Join-Path $root "src"

if (-not (Test-Path $out)) { New-Item -ItemType Directory -Path $out | Out-Null }

$cp = "`"$lib\*`""
$mainSrc = Join-Path $src "view\MainUI.java"

Write-Host "Bien dich CLIENT ..."
& javac -encoding UTF-8 -d $out -cp $cp -sourcepath $src $mainSrc 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "LOI bien dich. Kiem tra lib va JAR."
    exit 1
}
Write-Host "Bien dich xong."
Write-Host "Chay CLIENT (view.MainUI) ..."
# Can them $src vao classpath de tim file anh /images/icon/...
Set-Location $out
& java -cp ".;$lib\*;$src" view.MainUI
Set-Location $root
