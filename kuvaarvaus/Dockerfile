FROM maven:3-jdk-10 as build

WORKDIR /code

ADD pom.xml /code/pom.xml
ADD src /code/src

RUN mvn package


FROM openjdk:10-jre
COPY --from=build /code/target/app.war .

EXPOSE 8080
CMD ["java", "-jar", "./app.war"]
