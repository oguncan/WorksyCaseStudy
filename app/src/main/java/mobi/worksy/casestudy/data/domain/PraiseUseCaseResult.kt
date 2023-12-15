package mobi.worksy.casestudy.data.domain

import mobi.worksy.casestudy.data.model.PraiseItemModel
import mobi.worksy.casestudy.data.model.PraiseModel
import mobi.worksy.casestudy.util.Resource

sealed class PraiseUseCaseResult {
    data class Success(val praiseList: List<PraiseItemModel>) : PraiseUseCaseResult()
    data class Error(val message: String) : PraiseUseCaseResult()
    object Loading : PraiseUseCaseResult()
}