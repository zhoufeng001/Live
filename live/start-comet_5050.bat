cd ./live-comet
set MAVEN_OPTS= -Djetty.port=5050 -Djetty.ajp.port=5009 -Djetty.stop.port=5079 -Xrunjdwp:transport=dt_socket,address=5555,suspend=n,server=y -Xms256m -Xmx256m -Dcom.sun.management.jmxremote.port=5577 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/java_pid.hprof 
rem -Dio.netty.leakDetectionLevel=advanced
mvn -Dmaven.test.skip=true jetty:run