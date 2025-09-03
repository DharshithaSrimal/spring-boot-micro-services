# FROM container-registry.oracle.com/java/openjdk:17
FROM openjdk:17
EXPOSE 8761
ADD target/eureka-server.jar eureka-server.jar
ENTRYPOINT ["java","-jar","/eureka-server.jar"]

#Create an image
# docker build -t eureka-server .

#Run the container
# docker run -p 8761:8761 --name=eureka-server eureka-server.jar
