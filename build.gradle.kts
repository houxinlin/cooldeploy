import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    war
    kotlin("plugin.spring") version "1.5.31"
}

group = "com.hxl"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
}

dependencies {
    implementation("org.gradle:gradle-tooling-api:7.3")

// https://mvnrepository.com/artifact/org.gradle/gradle-tooling-api
//    implementation("org.gradle:gradle-tooling-api:7.3")
    implementation("com.jcraft:jsch:0.1.55")
    implementation("org.springframework.boot:spring-boot-starter-websocket:2.5.6")
    implementation("org.apache.maven.shared:maven-invoker:3.1.0")

//    implementation("org.gradle:gradle-tooling-api:7.3")


    implementation("com.alibaba:fastjson:1.2.78")
    implementation("org.eclipse.jgit:org.eclipse.jgit:5.2.1.201812262042-r"){
        exclude("com.jcraft","jsch")
    }
    implementation("org.springframework.boot:spring-boot-starter-websocket:2.5.6")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
