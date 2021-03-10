package ok.profile.app.ktor.service

import ok.profile.common.be.context.BeContext

interface ProfileService {
    fun get(context: BeContext)
    fun create(context: BeContext)
    fun update(context: BeContext)
    fun delete(context: BeContext)
    fun filter(context: BeContext)
}