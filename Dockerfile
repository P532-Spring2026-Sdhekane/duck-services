FROM eclipse-temurin:17
WORKDIR /home
RUN mkdir -p /home/ducks  # Create the ducks directory
COPY ./target/ducks-service-0.0.1-SNAPSHOT.jar ducks-service.jar
ENTRYPOINT ["java", "-jar", "ducks-service.jar"]