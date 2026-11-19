import net.fabricmc.loom.api.LoomGradleExtensionAPI

pluginManager.apply("net.fabricmc.fabric-loom-remap")
pluginManager.apply("hrf-common")

dependencies {
    "minecraft"("com.mojang:minecraft:${property("minecraft_version")}")
    "mappings"((project.extensions.getByName("loom") as LoomGradleExtensionAPI).officialMojangMappings())
    "modImplementation"("net.fabricmc:fabric-loader:${property("loader_version")}")
}
