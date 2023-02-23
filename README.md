# Pet Shop Catalogue
Reactive programming in Kotlin. CRUD REST API, Spring WebFlux and R2DBC.

To start -
* docker-compose up, don't forget to clean up docker images and rebuild between runs

To debug -
* start keycloak and mariadb, then ./gradlew bootRun <br/>_spring-boot-devtools along with gardle's `assemble --continuous
` task in a seperate terminal will automatically load changes to running app, as soon as you run the assemble task_
* or docker-compose up, don't forget to clean up docker images and rebuild between runs as this way won't provide a live reload, use a default 'Remote JVM Debug' configuration'

Actuator -
* http://localhost:9000/actuator
* you can also use `curl -X POST http://localhost:9000/actuator/shutdown`

Swagger -
* http://localhost:9000/v3/api-docs.yaml
* http://localhost:9000/v3/api-docs
* http://localhost:9000/webjars/swagger-ui/index.html
