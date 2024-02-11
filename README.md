# users-profiles-manager
- DB: Mysql 8.3
- API: JAVA JDK 21 + SpringBoot 3.2
- WEB: Angular 16 + NODE 20.11.0

## DB docker or local isntalation (user root without password)
    docker run -d -p 3306:3306 --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:8.3.0 (must be installed docker engine)

## API (./api/java) local without IDE (must be installed java 21 version)
    .\mvnw spring-boot:run
    **I put a collection on project root with api requests (insomnia export):
     Insomnia_collections_2024-02-10.yaml

## WEB (./web/angular) local without IDE (must ne isntalled node 20.11.0)
    in project folder: npm install
    npm run start
    http://localhost:4200

## Initial Access
    Admin: admin/gs3
    other: need signup

Soluções planejadas e não executadas:
 * melhoramento da interface
 * environments para API url e modo production
 * mensagens melhores apra usuario (locate and translate)
 * docker para web
 * docker compose
 * k8s e publicacao via gitactions
 *
