set AppName=%1
set user=%2
set pass=%3
cf login -u %user% -p %pass% -s production
start /W buildJar
cf push "%AppName%-prod" -p "target/%AppName%.jar" -f "prod-manifest.yml"
cf logout