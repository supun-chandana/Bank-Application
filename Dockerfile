FROM openjdk:17

EXPOSE 8080

ADD target/Bank-Application-0.0.1-SNAPSHOT.jar Bank-Application-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Bank-Application-0.0.1-SNAPSHOT.jar"]
