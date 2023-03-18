import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "bogdan.markov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val jacksonVersion: String by project

dependencies {
    testImplementation(kotlin("test"))

    implementation("dev.inmo", "tgbotapi", "4.1.1")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", jacksonVersion)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "14"
}

application {
    mainClass.set("MainKt")
}