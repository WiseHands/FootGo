FROM maven:3.6.1-jdk-11-slim AS builder
COPY . /src/
WORKDIR /src/
RUN mvn clean package -Dmaven.test.skip=true

FROM tomcat:9.0
RUN rm -Rf $CATALINA_HOME/webapps/ROOT
COPY --from=builder /src/target/footgo-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/ROOT.war
