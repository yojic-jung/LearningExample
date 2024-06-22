import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
    kotlin("plugin.jpa") version "1.9.20"
    kotlin("kapt") version "1.9.20"
    kotlin("plugin.allopen") version "1.9.20"
    kotlin("plugin.noarg") version "1.9.20"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
}

group = "com.yojic"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-aop")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // h2
    implementation("com.h2database:h2")

    // jdbcTemplate
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test> {
    useJUnitPlatform()
}
