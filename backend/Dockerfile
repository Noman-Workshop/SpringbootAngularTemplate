FROM amazoncorretto:11

RUN mkdir -p /app/backend

COPY ./target/todo-app.jar /app/backend/

WORKDIR /app/backend

CMD ["java", "-jar", "todo-app.jar"]
