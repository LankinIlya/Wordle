FROM gradle:jdk17 AS builder
WORKDIR /usr/app/
COPY . .
RUN gradle build


FROM openjdk:17-oracle
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/*.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","application.jar"]