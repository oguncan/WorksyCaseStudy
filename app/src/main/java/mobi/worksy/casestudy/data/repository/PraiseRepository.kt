package mobi.worksy.casestudy.data.repository

import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.data.model.PraiseModel
import mobi.worksy.casestudy.data.remote.PraiseApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

class PraiseRepository @Inject constructor(
    private val praiseApi: PraiseApi
) {
    suspend fun getBadgeList() : Response<PraiseModel> {
        val requestBody =
            "{\"endpoint\": \"praiseList\"}".toRequestBody("application/json".toMediaTypeOrNull())
        return praiseApi.getPraiseList(requestBody)
    }
}