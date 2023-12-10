plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.alonalbert"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.mail)
    implementation(libs.gson)
    implementation(libs.kotlin.test)
    implementation(libs.truth)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}