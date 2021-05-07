plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.5.0"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val mainSources = sourceSets["main"]
val appMainClass = "com.example.kotlin_gradle_jmh.AppKt"

application {
    // Define the main class for the application.
    mainClass.set(appMainClass)
}

tasks.register("smoke", JavaExec::class) {
    dependsOn(mainSources.classesTaskName)
    classpath = mainSources.runtimeClasspath
    main = appMainClass
    args(listOf("foobars", "foosbal"))
}

dependencies {
    // The bom pom defines the "Bill of Materials" for the Kotlin platform,
    // ensures versions of dependencies are aligned.
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
