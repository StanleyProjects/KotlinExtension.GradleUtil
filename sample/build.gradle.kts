repositories {
    mavenCentral()
    maven("https://repo.gradle.org/gradle/libs-releases-local")
}

plugins {
    id("org.gradle.application")
    id("org.jetbrains.kotlin.jvm")
}

application {
    mainClass.set("sp.service.sample.AppKt")
}

dependencies {
    implementation(project(":lib"))
    implementation("org.gradle:gradle-core-api:6.1.1")
    implementation("org.gradle:gradle-kotlin-dsl:6.1.1")
}