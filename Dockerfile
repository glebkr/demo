FROM openjdk:21
 ## allowing jenkins user to run sudo commands
RUN echo "jenkins ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers
 ## avoid typing sudo in command line
RUN echo "alias docker='sudo docker '" >> /home/jenkins/.bashrc
ADD target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8020
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]