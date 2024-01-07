FROM openjdk:21
ADD target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8020
ENTRYPOINT ["java","-jar","app.jar"]