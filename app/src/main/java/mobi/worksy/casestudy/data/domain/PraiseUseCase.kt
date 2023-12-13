package mobi.worksy.casestudy.data.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mobi.worksy.casestudy.data.model.PraiseModel
import mobi.worksy.casestudy.data.repository.PraiseRepository
import javax.inject.Inject

class PraiseUseCase @Inject constructor(
    private val praiseRepository: PraiseRepository
) {

    operator fun invoke(): Flow<PraiseUseCaseResult> = flow {
        emit(PraiseUseCaseResult.Loading)
        try {
            val response = praiseRepository.getBadgeList()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    emit(PraiseUseCaseResult.Success(data))
                } else {
                    emit(PraiseUseCaseResult.Error("Empty response"))
                }
            } else {
                emit(PraiseUseCaseResult.Error("Failed to fetch data"))
            }
        } catch (e: Exception) {
            emit(PraiseUseCaseResult.Error(e.message ?: "Unknown error occurred"))
        }
    }

}