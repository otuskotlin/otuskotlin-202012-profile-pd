plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    val ktorVersion: String by project
    val logbackVersion: String by project

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation(project(":ok-profile-common-be"))
    implementation(project(":ok-profile-transport-mp"))
    implementation(project(":ok-profile-transport-mappers"))

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}
