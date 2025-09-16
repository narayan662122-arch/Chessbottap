
plugins {
    id("com.android.application") version "8.2.0"
    kotlin("android")
}

android {
    namespace = "com.chessbot" // Use the package name from AndroidManifest.xml
    compileSdk = 34 // Use a recent compile SDK version

    defaultConfig {
        applicationId = "com.chessbot" // Use the package name from AndroidManifest.xml
        minSdk = 24 // Set a minimum SDK version
        targetSdk = 34 // Set a target SDK version
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Configure source sets to use the existing nested structure
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/src/main/AndroidManifest.xml")
            java.srcDirs("src/src/main/java")
            res.srcDirs("src/src/main/res")
            assets.srcDirs("src/src/main/assets")
        }
        getByName("test") {
             java.srcDirs("src/test/java")
        }
        getByName("androidTest") {
             java.srcDirs("src/androidTest/java")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Existing dependencies from top-level build.gradle.kts, moved here
    implementation(kotlin("stdlib"))
    implementation("com.squareup.okhttp3:okhttp:4.9.0") { // Add exclusion for duplicate OkHttp
        exclude(group = "com.squareup.okhttp3", module = "okhttp")
    }
    implementation("org.json:json:20210307") { # Assuming this is the correct artifact for org.json // Add exclusion for duplicate org.json
        exclude(group = "org.json", module = "json")
    }
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2") # Use a compatible version
    implementation("org.slf4j:slf4j-simple:1.7.30") # Use a compatible version

    // AndroidX dependencies (common for Android projects)
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0") # Use a recent version
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") # Use a recent version

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Include JARs from the libs directory
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
