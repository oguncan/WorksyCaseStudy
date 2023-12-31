package mobi.worksy.casestudy.data.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.data.repository.BadgeRepository
import javax.inject.Inject

class BadgeUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) {
    operator fun invoke(): Flow<BadgeUseCaseResult> = channelFlow {
        send(BadgeUseCaseResult.Loading)
        try {
            val response = badgeRepository.getBadgeList()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    send(BadgeUseCaseResult.Success(data))
                } else {
                    send(BadgeUseCaseResult.Error("Empty response"))
                }
            } else {
                send(BadgeUseCaseResult.Error("Failed to fetch data"))
            }
        } catch (e: Exception) {
            send(BadgeUseCaseResult.Error(e.message ?: "Unknown error occurred"))
        }
    }
}