FROM openjdk
LABEL maintainer = "abc@mail.com"
EXPOSE 8081
WORKDIR /app
COPY target/account-svc.jar /app/account-svc.jar
ENTRYPOINT ["java", "-jar", "account-svc.jar"]