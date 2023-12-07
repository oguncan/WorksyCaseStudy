package mobi.worksy.casestudy.data.remote

import mobi.worksy.casestudy.data.model.BadgeModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BadgeApi {
    @POST
    suspend fun getBadgeList(@Body requestBody: RequestBody) : Response<BadgeModel>
}