FROM node:24-alpine AS build
WORKDIR /workspace/frontend
ENV NODE_OPTIONS="--max-old-space-size=384"
COPY frontend/package.json frontend/package-lock.json ./
RUN npm ci
COPY frontend/ .
COPY contracts/ /workspace/contracts/
COPY backend/src/main/resources/game/ /workspace/backend/src/main/resources/game/
ENV VITE_API_BASE_URL=""
RUN npm run build

FROM nginxinc/nginx-unprivileged:stable-alpine AS runtime
COPY --from=build /workspace/frontend/dist /usr/share/nginx/html
COPY frontend/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 8080
HEALTHCHECK --interval=10s --timeout=3s --retries=6 CMD wget -q -O /dev/null http://127.0.0.1:8080/health || exit 1
