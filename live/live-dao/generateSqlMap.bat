@echo off
call mvn -Dmaven.test.skip=true install  mybatis-generator:generate 
echo �ļ��������
pause
