FROM maven:3-jdk-8-onbuild
EXPOSE 8080
CMD java -jar target/TAA-Project-0.0.1-SNAPSHOT.jar