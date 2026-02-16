@echo off
set "settings_file=%~dp0settings.xml"
if exist "%settings_file%" (
	powershell -NoProfile -Command "(Get-Content -Raw '%settings_file%') -replace '<string>mod</string>\s*<boolean>[^<]*</boolean>','<string>mod</string>`r`n     <boolean>true</boolean>' | Set-Content -NoNewline '%settings_file%'"
)
set "jar_file=%~dp0SCE-Universe.jar"
if not exist "%jar_file%" set "jar_file=%~dp0app.jar"
if not exist "%jar_file%" (
	echo Jar not found: SCE-Universe.jar or app.jar 1>&2
	exit /b 1
)
java -jar "%jar_file%" -mod 2> log.log
