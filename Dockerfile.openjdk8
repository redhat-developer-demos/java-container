FROM openjdk:8
ADD target/java-container.jar /usr/src/myapp/
WORKDIR /usr/src/myapp
EXPOSE 8080
CMD java -XX:+PrintFlagsFinal -jar java-container.jar