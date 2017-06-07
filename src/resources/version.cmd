echo OFF
SET VERSION=DEV
SET DATUM=%DATE%-%TIME:~0,5%

echo { > version.json
echo   "tagVersion": "%VERSION%", >> version.json
echo   "buildTimestamp": "%DATUM%" >> version.json
echo } >> version.json