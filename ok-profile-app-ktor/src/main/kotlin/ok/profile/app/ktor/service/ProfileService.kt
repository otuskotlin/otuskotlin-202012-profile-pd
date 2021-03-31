package ok.profile.app.ktor.service

import ok.profile.common.be.context.Context

interface ProfileService {
    fun get(context: Context)
    fun create(context: Context)
    fun update(context: Context)
    fun delete(context: Context)
    fun filter(context: Context)
}