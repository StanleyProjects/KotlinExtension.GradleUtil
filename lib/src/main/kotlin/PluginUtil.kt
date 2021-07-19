import org.gradle.plugin.use.PluginDependenciesSpec
import sp.kx.gradle.entity.Plugin

fun PluginDependenciesSpec.apply(plugin: Plugin, isWithVersion: Boolean = false) {
    if (isWithVersion) {
        id(plugin.name).version(plugin.version)
    } else {
        id(plugin.name)
    }
}

fun PluginDependenciesSpec.apply(
    first: Plugin,
    second: Plugin,
    vararg other: Plugin
) {
    id(first.name)
    id(second.name)
    other.forEach { id(it.name) }
}
