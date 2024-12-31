FROM openjdk:17

ENV PORT 8080
EXPOSE $PORT

ADD target/Bank-Application.jar Bank-Application.jar
ENTRYPOINT ["java", "-jar", "Bank-Application.jar"]
