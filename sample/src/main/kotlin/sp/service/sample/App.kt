package sp.service.sample

import addToClasspath
import org.gradle.kotlin.dsl.DependencyHandlerScope
import sp.kx.gradle.entity.Dependency
import sp.kx.gradle.entity.dependency

private fun addToClasspath(scope: DependencyHandlerScope, dependency: Dependency) {
    scope.addToClasspath(dependency)
}

private fun addToClasspath(
    scope: DependencyHandlerScope,
    group: String,
    name: String,
    version: String
) {
    addToClasspath(scope = scope, dependency = dependency(group = group, name = name, version = version))
}

fun main() {
    println("foo")
}
