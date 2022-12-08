FROM openjdk:17-oracle
ARG JAR_FILE=target/task775-0.1.0.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]