rootProject.name = "otuskotlin-202012-profile-pd"

pluginManagement {
    val kotlinVersion: String by settings
    val bmuschkoVersion: String by settings

    plugins {
        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        id("com.bmuschko.docker-java-application") version bmuschkoVersion
    }
}

include("ok-profile-common-mp")
include("ok-profile-common-be")
include("ok-profile-transport-mp")
include("ok-profile-transport-mappers")
include("ok-profile-app-ktor")
