plugins {
    base
}

allprojects {
    group = "com.adrianbukros.github.example"
    version = "0.1.0"

    buildscript {
        repositories {
            jcenter()
            maven("https://dl.bintray.com/kotlin/kotlin-dev/")
        }
    }

    repositories {
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-dev/")
    }
}
