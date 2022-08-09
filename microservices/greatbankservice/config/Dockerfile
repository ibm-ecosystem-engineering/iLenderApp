FROM anapsix/alpine-java:8
WORKDIR /
ADD app.jar app.jar
RUN sh -c 'touch /app.jar'
COPY app.jar /opt/
COPY support-files /opt/
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]
