plugins {
    kotlin("jvm")
}

group = "profile.pd"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    val kotestVersion: String by project

    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
