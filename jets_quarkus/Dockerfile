
FROM registry.access.redhat.com/ubi8/openjdk-21-runtime
WORKDIR /work
COPY target/quarkus-app/ .
EXPOSE 8081
CMD ["java", "-jar", "quarkus-run.jar"]