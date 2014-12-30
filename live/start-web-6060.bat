cd ./live-web
set MAVEN_OPTS= -Djetty.port=6060 -Djetty.ajp.port=6009 -Djetty.stop.port=6079 -Xrunjdwp:transport=dt_socket,address=6666,suspend=n,server=y -Xms256m -Xmx256m -Dcom.sun.management.jmxremote.port=6677 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/java_pid.hprof 
rem -Dio.netty.leakDetectionLevel=advanced
mvn -Dmaven.test.skip=true jetty:run