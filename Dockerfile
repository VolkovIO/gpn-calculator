FROM openjdk:11-jre

EXPOSE 8080

RUN adduser --system --home /var/cache/bootapp --shell /sbin/nologin bootapp;

COPY /target/gazprpomneft-0.0.1-SNAPSHOT.jar /opt/gazprpomneft-0.0.1-SNAPSHOT.jar

USER bootapp

ENTRYPOINT ["java","-jar","/opt/gazprpomneft-0.0.1-SNAPSHOT.jar"]
