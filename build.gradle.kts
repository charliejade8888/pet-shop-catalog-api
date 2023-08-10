import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    id("io.gitlab.arturbosch.detekt") version "1.22.0-RC3"
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("jvm") version "1.7.21"
}

group = "com.tyrell"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo1.maven.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.json:json:20220924")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.12")
//    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.12")

    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webflux-ui
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.2")


    implementation("org.mariadb:r2dbc-mariadb:1.1.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.liquibase:liquibase-core")
    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.testcontainers:testcontainers:1.17.5")
    testImplementation("org.testcontainers:junit-jupiter:1.17.5")
    testImplementation("org.testcontainers:mariadb:1.17.5")
    testImplementation("org.testcontainers:r2dbc:1.17.5")
    testImplementation("org.freemarker:freemarker:2.3.31")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.9.0")

    testImplementation("io.cucumber:cucumber-java:7.9.0")
    testImplementation("io.cucumber:cucumber-spring:7.9.0")
    testImplementation("io.cucumber:cucumber-junit:7.9.0")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:json-path")
    testImplementation("io.rest-assured:xml-path")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val SourceSet.kotlin: SourceDirectorySet
    get() = project.extensions.getByType<KotlinJvmProjectExtension>().sourceSets.getByName(name).kotlin

sourceSets {
    create("component-test") {
        kotlin.srcDirs("src/component-test")
        compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
    }
}

val componentTest = task<Test>("component-test") {
    description = "Runs the component test, use -d or -i flag to see output."
    group = "verification"
    testClassesDirs = sourceSets["component-test"].output.classesDirs
    classpath = sourceSets["component-test"].runtimeClasspath
    useJUnitPlatform()
    dependsOn("assemble")
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

//https://kotlin.github.io/kotlinx-kover/
koverReport {
    verify {
        rule {
            minBound(0)
        }
    }
    filters {
        includes {
            classes("com.tyrell.ReactiveKotlinApplication")
        }
    }
}
