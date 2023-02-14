FROM eclipse-temurin:11.0.18_10-jdk-alpine
COPY /build/libs/book-0.0.1-SNAPSHOT.jar book-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/book-0.0.1-SNAPSHOT.jar"]