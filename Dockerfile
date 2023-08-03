FROM openjdk:17
MAINTAINER PsiCom
COPY build/libs/pet-shop-catalog-0.0.1-SNAPSHOT.jar pet-shop-catalog-0.0.1-SNAPSHOT.jar
COPY ./scripts/wait-for-it.sh /wait-for-it.sh
RUN chmod +x wait-for-it.sh
CMD ./wait-for-it.sh 127.0.0.1:3306 --strict -t 120;./wait-for-it.sh 127.0.0.1:8180 --strict -t 120 -- java -jar /pet-shop-catalog-0.0.1-SNAPSHOT.jar
#to debug - uncomment below and add a default 'Remote JVM Debug' configuration!
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
#EXPOSE 5005
EXPOSE 9000
