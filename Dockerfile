FROM registry.yufuid.com/java:8-jre-arthas
EXPOSE 8080
ARG JAR_FILE

MAINTAINER daxue "daxue0929@qq.com"

ADD ./target/demo-test-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]


