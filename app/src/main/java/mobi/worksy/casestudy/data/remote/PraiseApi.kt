package mobi.worksy.casestudy.data.remote

import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.data.model.PraiseModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PraiseApi {
    @POST
    suspend fun getPraiseList(@Body requestBody: RequestBody) : Response<PraiseModel>
}