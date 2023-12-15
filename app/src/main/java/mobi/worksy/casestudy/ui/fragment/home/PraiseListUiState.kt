package mobi.worksy.casestudy.ui.fragment.home

import mobi.worksy.casestudy.data.model.PraiseItemModel

sealed class PraiseListUiState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): PraiseListUiState<T>(data)
    class Error<T>(message: String, data: T? = null): PraiseListUiState<T>(data, message)
    class Loading<T> : PraiseListUiState<T>()
}