plugins {
    application
    kotlin("jvm")
    id("com.bmuschko.docker-java-application")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

docker {
    javaApplication {
        baseImage.set("adoptopenjdk/openjdk11:alpine-jre")
        ports.set(listOf(8080))
        val imageName = project.name
        images.set(listOf(
            "$imageName:${project.version}",
            "$imageName:latest"
        ))
        jvmArgs.set(listOf("-Xms256m", "-Xmx512m"))
    }
}

dependencies {
    val ktorVersion: String by project
    val logbackVersion: String by project
    val ktorKafkaVersion: String by project
    val kafkaVersion: String by project

    implementation(kotlin("stdlib"))

    // ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    // logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // kafka
    implementation("com.github.Datana-company:ktor-kafka:$ktorKafkaVersion")
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")

    implementation(project(":ok-profile-common-be"))
    implementation(project(":ok-profile-transport-mp"))
    implementation(project(":ok-profile-transport-mappers"))
    implementation(project(":ok-profile-business-logic"))
}

/*
tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
 */