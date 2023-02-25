# Pet Shop Catalog
Reactive programming in Kotlin. CRUD REST API, Spring WebFlux and R2DBC.

To start -
* `docker-compose up`, don't forget to clean up docker images and rebuild between runs

To debug -
* start keycloak and mariadb, <br>`./gradlew bootRun` and then <br>`./gradlew assemble --continuous` in a seperate terminal <br>now _springboot-dev-tools_ will hot reload any changes
* or docker-compose up, don't forget to clean up docker images and rebuild between runs as this way won't provide a live reload, use a default 'Remote JVM Debug' configuration'

Actuator -
* http://localhost:9000/actuator
* you can also use `curl -X POST http://localhost:9000/actuator/shutdown`

Swagger -
* http://localhost:9000/v3/api-docs.yaml
* http://localhost:9000/v3/api-docs
* http://localhost:9000/webjars/swagger-ui/index.html

Other -
* run `./gradlew tasks` for full list of tasks available
* run `./gradlew cucumber` for out of process component test (BAT)
