FROM docker.io/primetoninc/jdk:1.8

ADD boss-service-user.jar ./

#Configuring time zone

RUN ln -sf /user/share/zoneinfo/Asia/Shanghai /etc/location
RUN echo "Asia/Shanghai" > /etc/timezone

#JAVA_OPTS SAMPLE -Xmx256m -XX:PermSize=64m -XX:MaxPermSize=128m
ENTRYPOINT java $JAVA_OPTS -jar boss-service-user.jar

#ADD Additional Arguments In CMD