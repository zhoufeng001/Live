@echo off
set "root=%cd%"
echo %root%
cd %root%/live-pojo
call mvn -Dmaven.test.skip=true install 

cd %root%/live-common
call mvn -Dmaven.test.skip=true install 

cd %root%/live-client
call mvn -Dmaven.test.skip=true install 

cd %root%/live-dao
call mvn -Dmaven.test.skip=true install 

cd %root%/live-service
call mvn -Dmaven.test.skip=true install 

cd %root%/live-rmiservice
call mvn -Dmaven.test.skip=true install 

cd %root%/live-comet
call mvn -Dmaven.test.skip=true install 

cd %root%/live-web
call mvn -Dmaven.test.skip=true install 

echo 全部安装完成

pause
