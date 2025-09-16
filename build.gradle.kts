
import org.gradle.api.tasks.Delete

plugins {
    // Add any top-level plugins here if necessary, e.g., for dependency management or build scans
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Specify the Android Gradle Plugin version
        classpath(kotlin("gradle-plugin", version = "1.9.0")) // Specify Kotlin plugin version
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}