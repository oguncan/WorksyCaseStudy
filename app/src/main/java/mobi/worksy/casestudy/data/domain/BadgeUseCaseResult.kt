package mobi.worksy.casestudy.data.domain

import mobi.worksy.casestudy.data.model.BadgeModel

sealed class BadgeUseCaseResult {
    data class Success(val badgeList: BadgeModel) : BadgeUseCaseResult()
    data class Error(val message: String) : BadgeUseCaseResult()
    object Loading : BadgeUseCaseResult()
}