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
