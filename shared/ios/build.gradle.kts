import org.jetbrains.kotlin.gradle.plugin.experimental.internal.KotlinNativeMainComponent
import org.jetbrains.kotlin.gradle.plugin.experimental.internal.OutputKind
import org.jetbrains.kotlin.gradle.plugin.experimental.plugins.kotlinNativeSourceSets
import org.jetbrains.kotlin.konan.target.KonanTarget

plugins {
    id("org.jetbrains.kotlin.platform.native") version "0.8.2"
}

sourceSets["main"].component(Action {
    target("ios_x64", "ios_arm64")
    if (this is KotlinNativeMainComponent) {
        baseName.set("Shared")
        extraOpts( "-module_name", "")
        outputKinds.add(OutputKind.FRAMEWORK)
    }
})

tasks {
    "copyFramework" {
        doLast {
            val buildDir = tasks.getByName("compileDebugFrameworkIos_x64KotlinNative").outputs.files.first().parent
            val configurationBuildDir: String by project
            copy {
                from(buildDir)
                into(configurationBuildDir)
            }
        }
    }
}

repositories {
    maven("https://dl.bintray.com/kotlin/ktor/")
    maven("https://dl.bintray.com/kotlin/kotlinx/")
}

dependencies {
    expectedBy(project(":shared:common"))
    implementation("io.ktor:ktor-client-ios:0.9.4")
    implementation("org.jetbrains.kotlinx:jsonparser-native:0.6.1")
}
