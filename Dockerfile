FROM openjdk:11
ADD target/addprofile-docker.jar addprofile-docker.jar
ENTRYPOINT ["java","-jar","/addprofile-docker.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 9002