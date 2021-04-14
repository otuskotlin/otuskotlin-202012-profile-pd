package ok.profile.business.logic

import ok.profile.business.logic.pipelines.*
import ok.profile.common.be.context.Context

class ProfileService : IProfileService {
    override suspend fun read(context: Context) {
        ProfileRead.execute(context)
    }

    override suspend fun create(context: Context) {
        ProfileCreate.execute(context)
    }

    override suspend fun update(context: Context) {
        ProfileUpdate.execute(context)
    }

    override suspend fun delete(context: Context) {
        ProfileDelete.execute(context)
    }

    override suspend fun filter(context: Context) {
        ProfileFilter.execute(context)
    }
}