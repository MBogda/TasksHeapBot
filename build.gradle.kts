import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    application
    kotlin("plugin.serialization") version "1.8.10"
}

group = "bogdan.markov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val tgbotapiVersion: String by project

val jacksonVersion: String by project
val kamlVersion: String by project
val kotlinxJsonVersion: String by project

val exposedVersion: String by project
val h2Version: String by project

dependencies {
    testImplementation(kotlin("test"))

    implementation("dev.inmo", "tgbotapi", tgbotapiVersion)

    // Serialization
    // todo: replace jackson with kotlin serialization (kaml)
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", jacksonVersion)
    implementation("com.charleskorn.kaml", "kaml", kamlVersion)
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", kotlinxJsonVersion)

    // Database
    implementation("org.jetbrains.exposed", "exposed-core", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-java-time", exposedVersion)
    implementation("com.h2database", "h2", h2Version)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "14"
}

application {
    mainClass.set("bogdan.markov.tasksheapbot.MainKt")
}
