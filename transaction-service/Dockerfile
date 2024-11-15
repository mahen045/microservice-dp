FROM openjdk
LABEL maintainer = "abc@mail.com"
EXPOSE 8082
WORKDIR /app
COPY target/transaction-svc.jar /app/transaction-svc.jar
ENTRYPOINT ["java", "-jar", "transaction-svc.jar"]