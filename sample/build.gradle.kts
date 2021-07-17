repositories {
    mavenCentral()
}

plugins {
    id("org.gradle.application")
    id("org.jetbrains.kotlin.jvm")
}

application {
    mainClass.set("sp.service.sample.AppKt")
}
