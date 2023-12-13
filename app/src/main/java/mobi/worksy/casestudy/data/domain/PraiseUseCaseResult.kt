package mobi.worksy.casestudy.data.domain

import mobi.worksy.casestudy.data.model.PraiseModel

sealed class PraiseUseCaseResult {
    data class Success(val praiseList: PraiseModel) : PraiseUseCaseResult()
    data class Error(val message: String) : PraiseUseCaseResult()
    object Loading : PraiseUseCaseResult()
}