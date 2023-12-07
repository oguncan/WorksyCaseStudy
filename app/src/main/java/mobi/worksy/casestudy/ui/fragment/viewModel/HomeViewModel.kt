package mobi.worksy.casestudy.ui.fragment.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mobi.worksy.casestudy.application.WorksyApplication
import mobi.worksy.casestudy.data.repository.BadgeRepository
import mobi.worksy.casestudy.data.repository.PraiseRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val badgeRepository: BadgeRepository,
    val praiseRepository: PraiseRepository,
    private val application: WorksyApplication
) : ViewModel() {

}