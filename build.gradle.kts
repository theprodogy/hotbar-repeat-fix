val versionProjects = project(":versions").subprojects

tasks.register("collectJars", Copy::class) {
    dependsOn(versionProjects.map { "${it.path}:build" })
    from(versionProjects.map { it.layout.buildDirectory.dir("libs") })
    include("*+mc*.jar")
    exclude("*-sources.jar", "*-dev.jar")
    into(layout.buildDirectory.dir("jars"))
}
