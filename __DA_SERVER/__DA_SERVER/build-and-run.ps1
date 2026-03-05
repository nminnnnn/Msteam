# Bien dich va chay SERVER (trong Cursor / PowerShell)
# Thu muc hien tai: __DA_SERVER\__DA_SERVER

$ErrorActionPreference = "Stop"
$root = $PSScriptRoot
$lib = Join-Path $root "lib"
$out = Join-Path $root "out"
$src = Join-Path $root "src"

# Tao thu muc out
if (-not (Test-Path $out)) { New-Item -ItemType Directory -Path $out | Out-Null }

$cp = "`"$lib\*`""
# Bo qua module-info.java (yeu cau netty/TimingFramework khong co trong lib)
$javaFiles = @(Get-ChildItem -Path $src -Recurse -Filter "*.java" | Where-Object { $_.Name -ne "module-info.java" } | ForEach-Object { $_.FullName })

Write-Host "Bien dich SERVER ($($javaFiles.Count) file) ..."
& javac -encoding UTF-8 -d $out -cp $cp $javaFiles 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "LOI bien dich. Kiem tra duong dan lib va JAR."
    exit 1
}
Write-Host "Bien dich xong."
Write-Host "Chay SERVER (view.Main) ..."
Set-Location $out
& java -cp ".;$lib\*" view.Main
Set-Location $root
