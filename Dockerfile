FROM openjdk:8

ADD target/rpn-evalutor-service-0.1.0.jar /opt/rpn/app.jar

EXPOSE 8080

WORKDIR /opt/rpn
ENTRYPOINT java $JAVA_OPTS -jar /opt/rpn/app.jar
