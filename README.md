# Pet Shop Catalog
Reactive programming in Kotlin. CRUD REST API, Spring WebFlux and R2DBC.

To start -
* `docker-compose up` in pet-shop-auth project
* `docker-compose up` in this project, don't forget to clean up docker images and rebuild between runs

To debug -
* start pet-shop-auth and mariadb, <br>`./gradlew bootRun` and then <br>`./gradlew assemble --continuous` in a seperate terminal <br>now _springboot-dev-tools_ will hot reload any changes
* or `docker-compose up` in both the pet-shop-auth project and here, don't forget to clean up docker images and rebuild between runs as this way won't provide a live reload, use a default 'Remote JVM Debug' configuration'

Actuator -
* http://localhost:9000/actuator
* you can also use `curl -X POST http://localhost:9000/actuator/shutdown`

Swagger -
* http://localhost:9000/v3/api-docs.yaml
* http://localhost:9000/v3/api-docs
* http://localhost:9000/webjars/swagger-ui/index.html

Other -
* `./gradlew tasks` for full list of tasks available
* `./gradlew component-test` for out of process component test (BAT)
* `./gradlew koverHtmlReport` for test coverage
* `./gradlew detekt` for static anaylsis, see detekt.yml for config
