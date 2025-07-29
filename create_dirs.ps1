$directories = @(
    "src\main\java\com\example\fantasy\domain\repository",
    "src\main\java\com\example\fantasy\domain\service",
    "src\main\java\com\example\fantasy\domain\exception",
    "src\main\java\com\example\fantasy\application",
    "src\main\java\com\example\fantasy\application\dto",
    "src\main\java\com\example\fantasy\application\mapper",
    "src\main\java\com\example\fantasy\application\port\in",
    "src\main\java\com\example\fantasy\application\port\out",
    "src\main\java\com\example\fantasy\application\service",
    "src\main\java\com\example\fantasy\application\exception",
    "src\main\java\com\example\fantasy\adapter",
    "src\main\java\com\example\fantasy\adapter\inbound\rest",
    "src\main\java\com\example\fantasy\adapter\inbound\messaging",
    "src\main\java\com\example\fantasy\adapter\outbound\persistence\entity",
    "src\main\java\com\example\fantasy\adapter\outbound\persistence\repository",
    "src\main\java\com\example\fantasy\adapter\outbound\persistence\mapper",
    "src\main\java\com\example\fantasy\adapter\outbound\persistence\CsvImport",
    "src\main\java\com\example\fantasy\adapter\outbound\mail",
    "src\main\java\com\example\fantasy\shared",
    "src\main\java\com\example\fantasy\shared\utils",
    "src\main\java\com\example\fantasy\shared\config",
    "src\main\resources",
    "src\main\resources\db\migration",
    "src\test\java\com\example\fantasy\domain\service"
)

foreach ($dir in $directories) {
    New-Item -Path $dir -ItemType Directory -Force
    Write-Host "Created directory: $dir"
}
