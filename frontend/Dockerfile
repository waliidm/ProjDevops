FROM node:18.10-alpine AS build
WORKDIR /app
RUN npm cache clean --force
COPY . .
RUN npm install --force
RUN npm run build --prod

FROM nginx:alpine
COPY --from=build /app/dist/* /usr/share/nginx/html

EXPOSE 80
