FROM fabric8/java-alpine-openjdk8-jdk:1.6.2

ENV JAVA_APP_JAR java-container.jar
ENV AB_OFF true
ENV JAVA_MAJOR_VERSION 7

EXPOSE 8080

ADD target/$JAVA_APP_JAR /deployments/