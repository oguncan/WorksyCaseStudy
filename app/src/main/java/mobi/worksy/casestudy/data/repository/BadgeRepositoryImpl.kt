package mobi.worksy.casestudy.data.repository

import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.data.remote.BadgeApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

class BadgeRepositoryImpl @Inject constructor(
    private val badgeApi: BadgeApi
) : BadgeRepository {

    override suspend fun getBadgeList(): Response<BadgeModel> {
        val requestBody =
            "{\"endpoint\": \"praiseList\"}".toRequestBody("application/json".toMediaTypeOrNull())
        return badgeApi.getBadgeList(requestBody)
    }


}