pluginManager.apply("net.fabricmc.fabric-loom")
pluginManager.apply("hrf-common")

dependencies {
    "minecraft"("com.mojang:minecraft:${property("minecraft_version")}")
    "implementation"("net.fabricmc:fabric-loader:${property("loader_version")}")
}
