FROM busybox:latest

RUN mkdir -p /app/frontend

COPY ./dist/todoApp-frontend/ /app/frontend/

WORKDIR /app/frontend

CMD ["httpd", "-f", "-v", "-p", "4200"]
