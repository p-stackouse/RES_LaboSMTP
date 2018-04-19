FROM java:openjdk-8-jre-alpine

ADD src /opt/src

WORKDIR /opt/src

CMD ["java", "-jar", "MockMock-1.4.0.one-jar.jar", "-p", "2526", "-h", "8282"]
