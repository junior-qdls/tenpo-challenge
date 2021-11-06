FROM openjdk:11
EXPOSE 8080
RUN mkdir /opt/app
ADD target/tenpo-challenge-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]


