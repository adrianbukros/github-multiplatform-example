import org.jetbrains.kotlin.config.AnalysisFlag.Flags.experimental
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("kotlin-platform-common") version "1.2.61"
    id("kotlinx-serialization") version "0.6.1"
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

repositories {
    maven("https://dl.bintray.com/kotlin/ktor/")
    maven("https://dl.bintray.com/kotlin/kotlinx/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.2.61")
    implementation("io.ktor:ktor-client:0.9.4-alpha-2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-jsonparser:0.6.1")
}
