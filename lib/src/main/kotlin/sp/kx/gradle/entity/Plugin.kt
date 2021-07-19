package sp.kx.gradle.entity

interface Plugin {
    val name: String
    val version: String
}

private class PluginImpl(
    override val name: String,
    override val version: String
) : Plugin

fun plugin(
    name: String,
    version: String
): Plugin {
    return PluginImpl(name = name, version = version)
}

fun plugin(name: String): Plugin {
    return plugin(name = name, version = "")
}
