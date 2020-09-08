FROM alpine:3.11.2
RUN apk add --no-cache openjdk11
COPY ../../../build/libs/mdm-depo-ms-0.0.1-SNAPSHOT.jar /app/depo.jar
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/depo.jar"]
