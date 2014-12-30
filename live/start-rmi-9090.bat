cd ./live-rmiservice
set MAVEN_OPTS= -Djetty.port=9090 -Djetty.ajp.port=9009 -Djetty.stop.port=9079 -Xrunjdwp:transport=dt_socket,address=9999,suspend=n,server=y -Xms256m -Xmx256m -Dcom.sun.management.jmxremote.port=9977 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/java_pid.hprof 
rem -Dio.netty.leakDetectionLevel=advanced
mvn -Dmaven.test.skip=true jetty:run