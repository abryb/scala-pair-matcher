FROM hseeberger/scala-sbt:11.0.6_1.3.9_2.13.1 AS build

COPY ./ ./

RUN sbt assembly

FROM java:8

COPY --from=build /root/target/scala-2.13/scala-pair-matcher-assembly-0.1.jar /app/app.jar

WORKDIR /data

ENTRYPOINT ["java", "-jar",  "/app/app.jar"]