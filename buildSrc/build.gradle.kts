plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://maven.fabricmc.net/") { name = "Fabric" }
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("net.fabricmc:fabric-loom:1.19.4")
    implementation("net.ltgt.gradle:gradle-errorprone-plugin:5.2.0")
}
