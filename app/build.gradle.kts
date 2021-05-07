plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.5.0"

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Apply the jmh plugin to add benchmark tasks and configuration.
    id("me.champeau.jmh") version "0.6.4"

    // Apply the jmhreport plugin to add support for rendering a pretty report.
    id("io.morethan.jmhreport") version "0.9.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

/*
 * Main app build setup
 */

val mainSources = sourceSets["main"]
val appMainClass = "com.example.kotlin_gradle_jmh.AppKt"

application {
    // Define the main class for the application.
    mainClass.set(appMainClass)
}

/*
 * Testing tasks
 */

tasks.register("smoke", JavaExec::class) {
    group = "test"
    description = "Run the main application with some test input"
    dependsOn(mainSources.classesTaskName)
    classpath = mainSources.runtimeClasspath
    main = appMainClass
    args(listOf("foo bar ", "foosball"))
}

/*
 * Benchmark setup
 */

// these paths are used for both the jmh and jmhReport plugins
val jmhReportsDir = "${project.buildDir}/reports/jmh"
val jmhResultsFile = "$jmhReportsDir/results.json"

// See the docs at https://github.com/melix/jmh-gradle-plugin
// for more configuration options.
jmh {
    jmhVersion.set("1.30")

    timeUnit.set("ms")

    // the default format is "CSV", we want "JSON" for the report plugin
    resultFormat.set("JSON")
    resultsFile.set(file(jmhResultsFile))
}

// These are the same as the defaults, but make them explicit.
jmhReport {
    jmhResultPath = jmhResultsFile
    jmhReportOutput = jmhReportsDir
}

// Make the report task always run after a successful benchmark.
tasks.named("jmh") {
    finalizedBy(tasks.named("jmhReport"))
}

/*
 * Dependencies
 */

dependencies {
    // The bom pom defines the "Bill of Materials" for the Kotlin platform,
    // ensures versions of dependencies are aligned.
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
