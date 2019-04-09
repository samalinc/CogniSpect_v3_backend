FROM openjdk:8-jdk-alpine
RUN mkdir -p /app
ENV PROJECT_HOME /app
ADD target/cognispect.jar $PROJECT_HOME/cognispect.jar
WORKDIR $PROJECT_HOME
CMD ["java", "-jar", "./cognispect.jar"]
