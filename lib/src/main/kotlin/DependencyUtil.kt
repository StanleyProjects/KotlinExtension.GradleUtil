import org.gradle.api.initialization.dsl.ScriptHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.ScriptHandlerScope
import sp.kx.gradle.entity.Dependency

fun Dependency.notation(): String {
    return "$group:$name:$version"
}

fun DependencyHandlerScope.addToConfiguration(configurationName: String, dependency: Dependency) {
    add(configurationName, dependency.notation())
}

fun DependencyHandlerScope.addToClasspath(dependency: Dependency) {
    addToConfiguration(ScriptHandler.CLASSPATH_CONFIGURATION, dependency)
}

fun ScriptHandlerScope.dependency(classpath: Dependency) {
    dependencies {
        addToClasspath(classpath)
    }
}

fun ScriptHandlerScope.dependencies(classpath: Set<Dependency>) {
    dependencies {
        classpath.forEach { addToClasspath(it) }
    }
}

fun DependencyHandlerScope.addToConfiguration(
    configurationName: String,
    first: Dependency,
    second: Dependency,
    vararg other: Dependency,
) {
    addToConfiguration(configurationName, first)
    addToConfiguration(configurationName, second)
    other.forEach {
        addToConfiguration(configurationName, dependency = it)
    }
}

fun DependencyHandlerScope.addToConfiguration(
    configurationName: String,
    dependencies: Set<Dependency>
) {
    dependencies.forEach {
        addToConfiguration(configurationName, dependency = it)
    }
}

fun DependencyHandlerScope.addToConfiguration(
    first: Pair<String, Set<Dependency>>,
    vararg other: Pair<String, Set<Dependency>>
) {
    addToConfiguration(configurationName = first.first, dependencies = first.second)
    other.forEach { (configurationName, dependencies) ->
        addToConfiguration(configurationName = configurationName, dependencies = dependencies)
    }
}

fun DependencyHandlerScope.implementation(dependency: Dependency) {
    addToConfiguration(configurationName = "implementation", dependency = dependency)
}
fun DependencyHandlerScope.implementation(
    first: Dependency,
    second: Dependency,
    vararg other: Dependency
) {
    implementation(first)
    implementation(second)
    other.forEach {
        implementation(dependency = it)
    }
}

fun DependencyHandlerScope.testImplementation(dependency: Dependency) {
    addToConfiguration(configurationName = "testImplementation", dependency = dependency)
}
fun DependencyHandlerScope.testImplementation(
    first: Dependency,
    second: Dependency,
    vararg other: Dependency
) {
    testImplementation(first)
    testImplementation(second)
    other.forEach {
        testImplementation(dependency = it)
    }
}

fun DependencyHandlerScope.testRuntimeOnly(dependency: Dependency) {
    addToConfiguration(configurationName = "testRuntimeOnly", dependency = dependency)
}
fun DependencyHandlerScope.testRuntimeOnly(
    first: Dependency,
    second: Dependency,
    vararg other: Dependency
) {
    testRuntimeOnly(first)
    testRuntimeOnly(second)
    other.forEach {
        testRuntimeOnly(dependency = it)
    }
}

fun DependencyHandlerScope.addToConfiguration(
    implementation: Set<Dependency>,
    testImplementation: Set<Dependency>
) {
    implementation.forEach {
        implementation(dependency = it)
    }
    testImplementation.forEach {
        testImplementation(dependency = it)
    }
}

fun DependencyHandlerScope.addToConfiguration(
    implementation: Set<Dependency>,
    testImplementation: Set<Dependency>,
    testRuntimeOnly: Set<Dependency>
) {
    implementation.forEach {
        implementation(dependency = it)
    }
    testImplementation.forEach {
        testImplementation(dependency = it)
    }
    testRuntimeOnly.forEach {
        testRuntimeOnly(dependency = it)
    }
}
