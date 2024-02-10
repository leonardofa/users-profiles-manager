# users-profiles-manager
API: JAVA: JDK 21 + SpringBoot 3.2

## DB
docker run -d -p 3306:3306 --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:8.3.0 (must be installed docker engine)

## API
Ways to run: 
    1) .\mvnw spring-boot:run - local without IDE (must be installed java 21 version)
    2) Docker
        docker build image: docker build . -t api 
        run image: docker run --network host api (with db on port 3306 - host)
    3)
