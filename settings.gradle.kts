pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        maven("https://dl.bintray.com/kotlin/kotlinx/")
    }
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id) {
                "kotlin-platform-common" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
                "kotlin-platform-android" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
                "kotlinx-serialization" -> useModule("org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:${requested.version}")
                "com.android.library" -> useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

enableFeaturePreview("GRADLE_METADATA")

include(":shared")
include(":shared:common")
include(":shared:ios")
include(":shared:android")
