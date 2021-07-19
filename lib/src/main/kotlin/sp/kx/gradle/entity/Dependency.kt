package sp.kx.gradle.entity

interface Dependency {
    val group: String
    val name: String
    val version: String
}

private class DependencyImpl(
    override val group: String,
    override val name: String,
    override val version: String
) : Dependency

fun dependency(
    group: String,
    name: String,
    version: String
): Dependency {
    return DependencyImpl(
        group = group,
        name = name,
        version = version
    )
}
