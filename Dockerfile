FROM openjdk:11-alpine

COPY target/DevOps_Project-*.jar /devops.jar

CMD ["java","-jar","/devops.jar"]
