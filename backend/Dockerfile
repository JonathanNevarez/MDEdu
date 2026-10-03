FROM maven:3.9.16-eclipse-temurin-21 AS build
WORKDIR /workspace
ENV MAVEN_OPTS="-Xmx384m"
COPY mde/ mde/
RUN mvn -f mde/pom.xml --batch-mode --no-transfer-progress -Dtarget.os=linux -Dtarget.ws=gtk -DskipTests install
COPY backend/pom.xml backend/pom.xml
COPY backend/src/main backend/src/main
RUN mvn -f backend/pom.xml --batch-mode --no-transfer-progress -DskipTests package

FROM eclipse-temurin:21-jre-jammy AS runtime
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -r /var/lib/apt/lists/*     && groupadd --gid 10001 mdedu && useradd --uid 10001 --gid mdedu --no-create-home mdedu
WORKDIR /app
COPY --from=build --chown=10001:10001 /workspace/backend/target/adaptativa-backend-0.1.0-SNAPSHOT.jar /app/app.jar
USER 10001:10001
ENV JAVA_TOOL_OPTIONS="-Xmx384m" SPRING_PROFILES_ACTIVE=prod BACKEND_ADDRESS=0.0.0.0
EXPOSE 8080
HEALTHCHECK --interval=10s --timeout=5s --start-period=45s --retries=12 CMD curl --fail --silent http://127.0.0.1:8080/actuator/health/readiness >/dev/null || exit 1
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
