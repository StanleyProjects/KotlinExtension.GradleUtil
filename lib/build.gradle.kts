repositories {
    mavenCentral()
    maven("https://repo.gradle.org/gradle/libs-releases-local")
}

plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation("org.gradle:gradle-core-api:6.1.1")
    implementation("org.gradle:gradle-kotlin-dsl:6.1.1")
}

project.version = "0.0.1"
val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

task<Jar>("assembleSnapshot") {
    dependsOn(compileKotlin)
    archiveBaseName.set("sp.kx.gradle")
    archiveVersion.set(project.version.toString() + "-SNAPSHOT")
    from(compileKotlin.destinationDir)
}
