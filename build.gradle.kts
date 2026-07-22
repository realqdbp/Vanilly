import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.fabric.loom)
	alias(libs.plugins.kotlin)
}

version = "0.0.3"
loom.log4jConfigs.from("log4j-dev.xml")

loom {
	splitEnvironmentSourceSets()

	mods {
		register(rootProject.name) {
			sourceSet(sourceSets.main.get())
		}
	}
}

dependencies {
	minecraft(libs.minecraft)
	implementation(libs.fabric.loader)
	implementation(libs.fabric.api)
    implementation(libs.fabric.kotlin)
}

tasks.processResources {
	inputs.property("version", version)

	filesMatching("fabric.mod.json") {
		expand(
			"modVersion" to version,
			"minecraftVersion" to libs.minecraft.get().version!!,
			"fabricLoaderVersion" to libs.fabric.loader.get().version!!,
			"fabricApiVersion" to libs.fabric.api.get().version!!,
			"fabricKotlinVersion" to libs.fabric.kotlin.get().version!!,
		)
	}
}

tasks.withType<JavaCompile>().configureEach { options.release = 25 }

kotlin { compilerOptions { jvmTarget = JvmTarget.JVM_25 } }

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
	inputs.property("projectName", rootProject.name)

	from("LICENSE") {
		rename { "${it}_${rootProject.name}" }
	}
}