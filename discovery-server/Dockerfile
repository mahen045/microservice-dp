FROM openjdk
LABEL maintainer = "abc@mail.com"
EXPOSE 8761
WORKDIR /app
COPY target/eureka-svc.jar /app/eureka-svc.jar
ENTRYPOINT ["java", "-jar", "eureka-svc.jar"]