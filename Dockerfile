FROM openjdk:8-alpine

COPY target/uberjar/restful.jar /restful/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/restful/app.jar"]
