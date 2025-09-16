plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "com.chessbot"
version = "1.0.0"

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    
    // HTTP client for Telegram API
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    
    // JSON parsing
    implementation("org.json:json:20231013")
    
    // Coroutines for async operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    
    // Logging
    implementation("org.slf4j:slf4j-simple:2.0.9")
    
    // Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("junit:junit:4.13.2")
}

application {
    mainClass.set("com.chessbot.MainActivityKt")
}

// Configure Kotlin to compile to JVM 17 bytecode (running on JVM 21)
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

// Configure Java compiler to use release 17 (running on JVM 21) 
tasks.withType<JavaCompile> {
    options.release.set(17)
}

tasks.test {
    useJUnitPlatform()
}

// Task to run the chess bot application  
tasks.register<JavaExec>("runChessBot") {
    dependsOn("classes")
    mainClass.set("com.chessbot.MainActivityKt") 
    classpath = sourceSets.main.get().runtimeClasspath
}

// Create Android-like APK structure for deployment
tasks.register<Zip>("buildApk") {
    dependsOn("build")
    archiveFileName.set("chess-bot-app.apk")
    destinationDirectory.set(layout.buildDirectory.dir("outputs/apk/release"))
    
    from(sourceSets.main.get().output.classesDirs) {
        into("classes")
    }
    from(sourceSets.main.get().runtimeClasspath) {
        into("lib")
    }
    from("src/main/res") {
        into("res")  
    }
    
    doLast {
        println("APK-like package created at: ${archiveFile.get().asFile.absolutePath}")
        println("This is a JVM application package suitable for deployment")
    }
}