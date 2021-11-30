plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    java
}

group = "lab.yearnlune"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.6")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.5.6")

    implementation("org.seleniumhq.selenium:selenium-java:4.1.0")
    implementation("org.jsoup:jsoup:1.14.3")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
    implementation(kotlin("stdlib"))

    runtimeOnly("com.h2database:h2:1.4.200")

    testImplementation("org.jetbrains.kotlin", "kotlin-test-junit5", "1.3.72")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.6") {
        exclude ("junit", "junit")
    }

    testImplementation(kotlin("test"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
