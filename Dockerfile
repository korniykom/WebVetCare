FROM gradle:9.3-jdk21 AS builder

WORKDIR /app

COPY . .

RUN ./gradlew :composeApp:jsBrowserDistribution --no-daemon

FROM nginx:alpine

COPY --from=builder /app/composeApp/build/dist/js/productionExecutable /usr/share/nginx/html

EXPOSE 80
