package mobi.worksy.casestudy.ui.fragment.home

import mobi.worksy.casestudy.data.model.PraiseItemModel

sealed class BadgeSliderUiState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): BadgeSliderUiState<T>(data)
    class Error<T>(message: String, data: T? = null): BadgeSliderUiState<T>(data, message)
    class Loading<T> : BadgeSliderUiState<T>()
}