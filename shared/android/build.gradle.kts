import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("com.android.library") version "3.1.4"
    id("kotlin-platform-android") version "1.2.61"
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

android {
    compileSdkVersion(27)
    defaultConfig {
        minSdkVersion(15)
        targetSdkVersion(27)
        versionCode = 1
        versionName = "0.1.0"
    }
}

repositories {
    google()
    maven("https://dl.bintray.com/kotlin/ktor/")
    maven("https://dl.bintray.com/kotlin/kotlinx/")
}

dependencies {
    expectedBy(project(":shared:common"))
    implementation(kotlin("stdlib-jdk7"))
    implementation("io.ktor:ktor-client-android:0.9.4-alpha-2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.6.1")
}
