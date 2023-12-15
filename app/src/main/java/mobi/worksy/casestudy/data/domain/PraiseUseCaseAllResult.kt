package mobi.worksy.casestudy.data.domain

import mobi.worksy.casestudy.data.model.PraiseItemModel

sealed class PraiseUseCaseAllResult {
    data class Success(val praiseAllList: List<PraiseItemModel>) : PraiseUseCaseAllResult()
    data class Error(val message: String) : PraiseUseCaseAllResult()
    object Loading : PraiseUseCaseAllResult()
}