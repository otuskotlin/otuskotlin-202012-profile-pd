plugins {
    kotlin("jvm")
}

dependencies {
    val kotestVersion: String by project

    implementation(kotlin("stdlib"))
    implementation(project(":ok-profile-common-be"))
    implementation(project(":ok-profile-pipelines-mp"))
    implementation(project(":ok-profile-pipelines-validation-mp"))
    implementation(project(":ok-profile-common-mp"))

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}