# Pet Shop Catalog
Reactive programming in Kotlin. 
CRUD REST API, Spring WebFlux and R2DBC.<br>
OAUTH2 with Keycloak, see [Spring Security 6 Zero to Master along with JWT,OAUTH2](https://www.udemy.com/course/spring-security-zero-to-master/?couponCode=SKILLS4SALEA) _sections 13, 14, 15, and 16_

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
* `./gradlew clean component-test -i` for out of process component test (BAT)
* `./gradlew koverHtmlReport` for test coverage
* `./gradlew detekt` for static anaylsis, see detekt.yml for config
