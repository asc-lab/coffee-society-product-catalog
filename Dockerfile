FROM openjdk:8-alpine
MAINTAINER ASC-LAB
RUN apk --no-cache add curl
EXPOSE 8080
COPY productcatalog-web/target/productcatalog-web*.jar product-catalog.jar
CMD java ${JAVA_OPTS} -jar product-catalog.jar
