FROM gradle:8.13-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle :composeApp:jsBrowserDistribution --no-daemon

FROM nginx:alpine

COPY --from=builder /app/composeApp/build/dist/js/productionExecutable /usr/share/nginx/html

EXPOSE 80