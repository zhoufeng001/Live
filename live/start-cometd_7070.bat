cd ./live-comet
set MAVEN_OPTS= -Djetty.port=7070 -Djetty.ajp.port=7009 -Djetty.stop.port=7079 -Xrunjdwp:transport=dt_socket,address=7777,suspend=n,server=y -Xms256m -Xmx256m -Dcom.sun.management.jmxremote.port=7077 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/java_pid.hprof 
rem -Dio.netty.leakDetectionLevel=advanced
mvn -Dmaven.test.skip=true jetty:run