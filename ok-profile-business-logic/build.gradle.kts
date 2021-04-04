plugins {
    kotlin("jvm")
}

dependencies {
    val kotestVersion: String by project

    implementation(kotlin("stdlib"))
    implementation(project(":ok-profile-common-be"))
    implementation(project(":ok-profile-pipelines-mp"))

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}