FROM openjdk:21
WORKDIR /app
COPY ./target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8020
CMD ["java","-jar","app.jar"]