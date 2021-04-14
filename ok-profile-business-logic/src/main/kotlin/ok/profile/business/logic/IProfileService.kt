package ok.profile.business.logic

import ok.profile.common.be.context.Context

interface IProfileService {
    suspend fun read(context: Context)
    suspend fun create(context: Context)
    suspend fun update(context: Context)
    suspend fun delete(context: Context)
    suspend fun filter(context: Context)
}