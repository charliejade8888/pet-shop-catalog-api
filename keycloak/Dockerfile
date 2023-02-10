FROM openjdk:11
MAINTAINER PsiCom
COPY ./ keycloak
ENTRYPOINT ["keycloak/bin/kc.sh","start-dev","--http-port", "8180"]
EXPOSE 8180
# note changes will only be saved to docker image, and will be lost if image removed
# to persist changes that survive cleaning docker env, use standalone instance in keycloak folder

