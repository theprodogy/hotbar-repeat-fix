import net.ltgt.gradle.errorprone.errorprone

plugins {
    java
    id("net.ltgt.errorprone")
}

val javaVersion = (property("java_version") as String).toInt()
val minecraftCompat = property("minecraft_compat") as String
val loaderVersion = property("loader_version") as String

version = "${property("mod_version")}+mc$minecraftCompat"
group = property("maven_group") as String

base {
    archivesName = property("archives_base_name") as String
}

sourceSets.main {
    java.srcDir(rootProject.file("common/src/main/java"))
    resources.srcDir(rootProject.file("common/src/main/resources"))
}

dependencies {
    compileOnly("org.jspecify:jspecify:1.0.1")
    errorprone("com.google.errorprone:error_prone_core:2.53.0")
    errorprone("com.uber.nullaway:nullaway:0.14.4")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release = javaVersion
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
    options.errorprone {
        disableWarningsInGeneratedCode = true
        option("UnusedMethod:ExemptingMethodAnnotations", "org.spongepowered.asm.mixin.injection.Inject,com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation")
        error("NullAway")
        option("NullAway:OnlyNullMarked", "true")
        option("NullAway:JSpecifyMode", "true")
    }
}

val expandProps = mapOf(
    "version" to project.version.toString(),
    "minecraft_compat" to minecraftCompat,
    "java_version" to javaVersion.toString(),
    "loader_version" to loaderVersion
)

tasks.processResources {
    inputs.properties(expandProps)
    filesMatching(listOf("fabric.mod.json", "*.mixins.json")) {
        expand(expandProps)
    }
}

tasks.jar {
    from(rootProject.file("LICENSE"))
}
