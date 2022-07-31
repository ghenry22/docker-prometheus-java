FROM alpine:latest

COPY ./data/serverapp/src/server /usr/src/serverapp
WORKDIR /usr/src/serverapp
RUN apk --no-cache add openjdk17 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
RUN apk add bash vim curl wget jq docker git tar unzip bash-completion ca-certificates gradle maven
RUN /usr/src/serverapp/gradlew build
ENTRYPOINT ["java","-jar","/usr/src/serverapp/build/libs/server-0.0.1-SNAPSHOT.jar"]