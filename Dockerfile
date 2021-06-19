FROM adoptopenjdk/openjdk11-openj9:jre-11.0.7_10_openj9-0.20.0-alpine

ADD /target/book-store-exec.jar /opt/book-store-exec.jar

EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -jar /opt/book-store-exec.jar