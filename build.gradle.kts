import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "bogdan.markov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val tgbotapiVersion: String by project
val jacksonVersion: String by project
val exposedVersion: String by project
val h2Version: String by project

dependencies {
    testImplementation(kotlin("test"))

    implementation("dev.inmo", "tgbotapi", tgbotapiVersion)

    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", jacksonVersion)

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
