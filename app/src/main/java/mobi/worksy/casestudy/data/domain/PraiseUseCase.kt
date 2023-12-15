package mobi.worksy.casestudy.data.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import mobi.worksy.casestudy.data.model.PraiseItemModel
import mobi.worksy.casestudy.data.model.PraiseModel
import mobi.worksy.casestudy.data.repository.PraiseRepository
import mobi.worksy.casestudy.util.PAGE_SIZE
import javax.inject.Inject

class PraiseUseCase @Inject constructor(
    private val praiseRepository: PraiseRepository
) {

    private var allPraiseData: List<PraiseItemModel>? = null

    suspend fun getAllPraiseData(): Flow<PraiseUseCaseAllResult> = channelFlow {
        send(PraiseUseCaseAllResult.Loading)
        try {
            val response = praiseRepository.getPraiseModel()
            if (response.isSuccessful) {
                val data = response.body()?.praiseItem ?: emptyList()
                send(PraiseUseCaseAllResult.Success(data))
            } else {
                send(PraiseUseCaseAllResult.Error("Failed to fetch data"))
            }
        } catch (e: Exception) {
            send(PraiseUseCaseAllResult.Error(e.message ?: "Unknown error occurred"))
        }
    }

    operator fun invoke(currentPage: Int): Flow<PraiseUseCaseResult> = channelFlow {
        send(PraiseUseCaseResult.Loading)
        try {
            val startIndex = (currentPage - 1) * PAGE_SIZE
            val endIndex = startIndex + PAGE_SIZE

            val allData = if (allPraiseData.isNullOrEmpty()) {
                val response = praiseRepository.getPraiseModel()
                if (response.isSuccessful) {
                    val data = response.body()?.praiseItem ?: emptyList()
                    allPraiseData = data
                    data
                } else {
                    send(PraiseUseCaseResult.Error("Failed to fetch all data"))
                    return@channelFlow
                }
            } else {
                allPraiseData!!
            }

            if (startIndex >= allData.size) {
                send(PraiseUseCaseResult.Error("Requested page is out of bounds"))
            } else {
                val paginatedData = allData.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(allData.size))
                send(PraiseUseCaseResult.Success(paginatedData))
            }
        } catch (e: Exception) {
            send(PraiseUseCaseResult.Error(e.message ?: "Unknown error occurred"))
        }
    }

}