buildscript {
    repositories {
        mavenCentral()
        maven("https://repo.gradle.org/gradle/libs-releases-local")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("org.gradle:gradle-core-api:6.1.1")
    }
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}
