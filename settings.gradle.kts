pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "com.android.application") {
useVersion("8.1.0")
      }
      if (requested.id.id == "org.jetbrains.kotlin.android") {
useVersion("1.8.0")
      }
    }
  }
}

rootProject.name = "project"
include(":app")