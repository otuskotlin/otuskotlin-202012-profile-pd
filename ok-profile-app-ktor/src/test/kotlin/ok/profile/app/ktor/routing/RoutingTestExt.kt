package ok.profile.app.ktor.routing

import io.ktor.application.*
import ok.profile.app.ktor.module
import ok.profile.app.ktor.service.InMemoryProfileService
import ok.profile.app.ktor.service.ProfileService
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

internal fun Application.testInit() {
    module(testing = true)
    di {
        bind<ProfileService>() with singleton { InMemoryProfileService() }
    }
}