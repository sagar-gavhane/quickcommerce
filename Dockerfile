FROM maven:3.6-openjdk-17

ENV SPRING_PROFILES_ACTIVE=prod

COPY src /app/src

COPY pom.xml /app

WORKDIR /app

RUN mvn -f pom.xml clean package

EXPOSE 8080

ENTRYPOINT java -jar -Dspring.profiles.active=prod /app/target/quickcommerce-0.0.1-SNAPSHOT.jar