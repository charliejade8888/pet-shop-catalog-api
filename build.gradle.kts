import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlinx.kover") version "0.9.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("jvm") version "1.9.22"
}
// TODO comment out kover/detekt, fix the rest then start from scratch via google/their docs!! - run build/boot first to find failure
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
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.json:json:20250107")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.1.0")

    implementation("org.mariadb:r2dbc-mariadb:1.1.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.liquibase:liquibase-core:4.23.2")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.testcontainers:testcontainers:1.19.0")
    testImplementation("org.testcontainers:junit-jupiter:1.19.0")
    testImplementation("org.testcontainers:mariadb:1.19.0")
    testImplementation("org.testcontainers:r2dbc:1.19.0")
    testImplementation("org.freemarker:freemarker:2.3.32")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.cucumber:cucumber-java:7.15.0")
    testImplementation("io.cucumber:cucumber-spring:7.15.0")
    testImplementation("io.cucumber:cucumber-junit:7.15.0")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:json-path:5.3.0")
    testImplementation("io.rest-assured:xml-path:5.3.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
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
    description = "Runs the component test, use clean or --rerun-tasks to re-run, use -d or -i flag to see output"
    group = "verification"
    testClassesDirs = sourceSets["component-test"].output.classesDirs
    classpath = sourceSets["component-test"].runtimeClasspath
    dependsOn("assemble")
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

kover {
    coverage {
        verify {
            rule {
                bound {
                    minValue = 0
                }
            }
        }
    }
    filters {
        includes {
            classes("com.tyrell.ReactiveKotlinApplication")
        }
    }
}