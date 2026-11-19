pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "hotbar-repeat-fix"

listOf(
    "1.21.11",
    "26.1",
    "26.2",
    "26.3",
).forEach { include("versions:$it") }
