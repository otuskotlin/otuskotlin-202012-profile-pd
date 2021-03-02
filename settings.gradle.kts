rootProject.name = "otuskotlin-202012-profile-pd"

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
    }
}

include("ok-profile-common-mp")
include("ok-profile-common-be")
include("ok-profile-transport-main-mp")
