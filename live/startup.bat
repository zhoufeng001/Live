echo off
SET PROJECT_HOME=%cd%
ECHO ��Ŀ��ַ:"%PROJECT_HOME%"

:main_command
ECHO.
ECHO.�������̣�1��install������Ŀ��2������redis��3����rmiservice��4������web��5������comet��6����nginx
ECHO.
ECHO 1-����rmiservice
ECHO.
ECHO 2-����web
ECHO.
ECHO 3-����comet
ECHO.
ECHO 4-����redis����
ECHO.
ECHO 5-����redis�ͻ���
ECHO.
ECHO 6-����nginx
ECHO.
ECHO 7-���rmiservice��web��comet��scheduled
ECHO.
ECHO 8-install������Ŀ
ECHO.
ECHO 0-�˳��˵�

set /p isopt=��ѡ�����
if /i "%isopt%"=="1" goto mvn_start_rmiservice
if /i "%isopt%"=="2" goto mvn_start_web
if /i "%isopt%"=="3" goto mvn_start_comet
if /i "%isopt%"=="4" goto start_redis_server
if /i "%isopt%"=="5" goto start_redis_client
if /i "%isopt%"=="6" goto start_nginx
if /i "%isopt%"=="7" goto package
if /i "%isopt%"=="8" goto mvn_install_all
if /i "%isopt%"=="0" exit
echo ��ѡ��������ǣ�%isopt%


:mvn_start_rmiservice
	cd %PROJECT_HOME%/live-rmiservice
	start /HIGH call jetty_9090.bat
	goto main_command


:mvn_start_web
	cd %PROJECT_HOME%/live-web
	start /HIGH call jetty_6060.bat
	goto main_command

:mvn_start_comet
	cd %PROJECT_HOME%/live-comet
	start /HIGH call jetty_5050.bat
	goto main_command

:package
	cd %PROJECT_HOME%/live-rmiservice
	start /HIGH call package.bat
	cd %PROJECT_HOME%/live-web
	start /HIGH call package.bat
	cd %PROJECT_HOME%/live-comet
	start /HIGH call package.bat
	cd %PROJECT_HOME%/live-scheduled
	start /HIGH call package.bat
	goto main_command

:start_redis_server
	cd %PROJECT_HOME%/redis-windows
	start /HIGH call start-server.bat
	goto main_command

:start_redis_client
	cd %PROJECT_HOME%/redis-windows
	start /HIGH call start-client.bat
	goto main_command

:start_nginx
	cd %PROJECT_HOME%/live-web-resource/nginx
	start /HIGH call nginx.exe
	goto main_command

:mvn_install_all
	cd %PROJECT_HOME%
	start /HIGH mvn install  -Dmaven.test.skip=true
	goto main_command
