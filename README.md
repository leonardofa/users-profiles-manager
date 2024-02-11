# users-profiles-manager
DB: Docker or Mysql local user root without password
API: JAVA JDK 21 + SpringBoot 3.2
WEB: NODE 20.11.0

## DB
    docker run -d -p 3306:3306 --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:8.3.0 (must be installed docker engine)

## API
    .\mvnw spring-boot:run - local without IDE (must be installed java 21 version)

## WEB
    in project folder: npm install
    npm run start