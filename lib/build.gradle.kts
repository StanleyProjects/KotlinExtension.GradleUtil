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

project.version = "0.0.2"
val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

object MavenUtil {
    private const val MAVEN_APACHE_URL = "http://maven.apache.org"
    private fun mavenApacheUrlPom(modelVersion: String): String {
        return "$MAVEN_APACHE_URL/POM/$modelVersion"
    }

    fun pom(
        modelVersion: String,
        groupId: String,
        artifactId: String,
        version: String,
        packaging: String
    ): String {
        val mavenApacheUrlPom = mavenApacheUrlPom(modelVersion = modelVersion)
        val project = setOf(
            "xsi:schemaLocation" to "$mavenApacheUrlPom $MAVEN_APACHE_URL/xsd/maven-$modelVersion.xsd",
            "xmlns" to mavenApacheUrlPom,
            "xmlns:xsi" to "http://www.w3.org/2001/XMLSchema-instance"
        ).joinToString(separator = " ") { (key, value) ->
            "$key=\"$value\""
        }
        return setOf(
            "modelVersion" to modelVersion,
            "groupId" to groupId,
            "artifactId" to artifactId,
            "version" to version,
            "packaging" to packaging
        ).joinToString(
            prefix = "<project $project>",
            separator = "",
            postfix = "</project>"
        ) { (key, value) ->
            "<$key>$value</$key>"
        }
    }
}

"Snapshot".also { type ->
    val groupId = "com.github.kepocnhh"
    val artifactId = rootProject.name
    val versionName = project.version.toString() + "-SNAPSHOT"
    task<Jar>("assemble$type") {
        dependsOn(compileKotlin)
        archiveBaseName.set(artifactId)
        archiveVersion.set(versionName)
        from(compileKotlin.destinationDir)
    }
    task("assemble${type}Pom") {
        doLast {
            val parent = File(buildDir, "libs")
            if (!parent.exists()) parent.mkdirs()
            val file = File(parent, "$artifactId-$versionName.pom")
            if (file.exists()) file.delete()
            file.createNewFile()
            check(file.exists()) { "File by path: ${file.absolutePath} must be exists!" }
            val text = MavenUtil.pom(
                modelVersion = "4.0.0",
                groupId = groupId,
                artifactId = artifactId,
                version = versionName,
                packaging = "jar"
            )
            file.writeText(text)
        }
    }
}
