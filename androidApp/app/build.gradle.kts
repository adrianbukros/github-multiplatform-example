import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

android {
    buildToolsVersion = "28.0.2"
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        multiDexEnabled = true
        versionCode = 1
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:0.23.4")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    implementation("com.android.support:appcompat-v7:28.0.0-rc02")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    implementation("com.android.support:recyclerview-v7:28.0.0-rc02")

    implementation("com.adrianbukros.github.example:android:0.1.0")
}
