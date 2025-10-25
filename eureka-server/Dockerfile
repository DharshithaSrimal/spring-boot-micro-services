# FROM container-registry.oracle.com/java/openjdk:17
FROM openjdk:17-jdk-slim
EXPOSE 8761
ADD target/eureka-server.jar ./eureka-server.jar
ENTRYPOINT ["java","-jar","/eureka-server.jar"]

#Create an image
# docker build -t eureka-server:0.1 .

#Run the container
# docker run -p 8761:8761 --name=eureka-server -d eureka-server:0.1
# or
# docker run -p 8761:8761 \
#  -e EUREKA_HOST=$PUBLIC_IP \
#  --name=eureka-server -d eureka-server:0.1
