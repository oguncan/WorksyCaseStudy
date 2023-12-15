package mobi.worksy.casestudy.data.repository

import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.data.remote.BadgeApi
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

interface BadgeRepository {

    suspend fun getBadgeList() : Response<BadgeModel>

}