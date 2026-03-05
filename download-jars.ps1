# Script tai cac thu vien JAR cho Server va Client (chay trong PowerShell)
# Chay: .\download-jars.ps1

$ErrorActionPreference = "Stop"
$base = "d:\year4\LTM\DACS4_SourceCode"

# === SERVER lib ===
$serverLib = Join-Path $base "__DA_SERVER\__DA_SERVER\lib"
Write-Host "Tai JAR cho SERVER vao $serverLib"
$serverJars = @(
    @{
        Url = "https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.2.0/mysql-connector-j-8.2.0.jar"
        Name = "mysql-connector-j-8.2.0.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/org/json/json/20230227/json-20230227.jar"
        Name = "json-20230227.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/com/miglayout/miglayout-swing/11.4.2/miglayout-swing-11.4.2.jar"
        Name = "miglayout-swing-11.4.2.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/net/java/dev/timingframework/timingframework/1.0/timingframework-1.0.jar"
        Name = "timingframework-1.0.jar"
    }
)
foreach ($j in $serverJars) {
    $out = Join-Path $serverLib $j.Name
    if (-not (Test-Path $out)) {
        Write-Host "  Dang tai $($j.Name) ..."
        Invoke-WebRequest -Uri $j.Url -OutFile $out -UseBasicParsing
        Write-Host "  OK."
    } else {
        Write-Host "  Da co $($j.Name), bo qua."
    }
}

# MigLayout core (miglayout-swing phu thuoc vao miglayout-core)
$migCore = @{
    Url = "https://repo1.maven.org/maven2/com/miglayout/miglayout-core/11.4.2/miglayout-core-11.4.2.jar"
    Name = "miglayout-core-11.4.2.jar"
}
$outCore = Join-Path $serverLib $migCore.Name
if (-not (Test-Path $outCore)) {
    Write-Host "  Dang tai $($migCore.Name) ..."
    Invoke-WebRequest -Uri $migCore.Url -OutFile $outCore -UseBasicParsing
    Write-Host "  OK."
}

# === CLIENT lib ===
$clientLib = Join-Path $base "__DA_CLIENT\__DA_CLIENT\lib"
Write-Host "Tai JAR cho CLIENT vao $clientLib"
$clientJars = @(
    @{
        Url = "https://repo1.maven.org/maven2/org/json/json/20230227/json-20230227.jar"
        Name = "json-20230227.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar"
        Name = "flatlaf-3.2.5.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/com/miglayout/miglayout-swing/11.4.2/miglayout-swing-11.4.2.jar"
        Name = "miglayout-swing-11.4.2.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/com/miglayout/miglayout-core/11.4.2/miglayout-core-11.4.2.jar"
        Name = "miglayout-core-11.4.2.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/eu/mindfusion/scheduling/2.2.2/scheduling-2.2.2.jar"
        Name = "scheduling-2.2.2.jar"
    },
    @{
        Url = "https://repo1.maven.org/maven2/com/toedter/jcalendar/1.4/jcalendar-1.4.jar"
        Name = "jcalendar-1.4.jar"
    }
)
foreach ($j in $clientJars) {
    $out = Join-Path $clientLib $j.Name
    if (-not (Test-Path $out)) {
        Write-Host "  Dang tai $($j.Name) ..."
        Invoke-WebRequest -Uri $j.Url -OutFile $out -UseBasicParsing
        Write-Host "  OK."
    } else {
        Write-Host "  Da co $($j.Name), bo qua."
    }
}

Write-Host "Xong. Kiem tra thu muc lib:"
Write-Host "  Server: $serverLib"
Get-ChildItem $serverLib -Filter "*.jar" | ForEach-Object { Write-Host "    - $($_.Name)" }
Write-Host "  Client: $clientLib"
Get-ChildItem $clientLib -Filter "*.jar" | ForEach-Object { Write-Host "    - $($_.Name)" }
