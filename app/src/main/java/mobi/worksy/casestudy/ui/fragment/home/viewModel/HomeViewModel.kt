package mobi.worksy.casestudy.ui.fragment.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mobi.worksy.casestudy.application.WorksyApplication
import mobi.worksy.casestudy.data.domain.BadgeUseCase
import mobi.worksy.casestudy.data.domain.BadgeUseCaseResult
import mobi.worksy.casestudy.data.domain.PraiseUseCase
import mobi.worksy.casestudy.data.domain.PraiseUseCaseAllResult
import mobi.worksy.casestudy.data.domain.PraiseUseCaseResult
import mobi.worksy.casestudy.data.model.BadgeGroupModel
import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.data.model.PraiseItemModel
import mobi.worksy.casestudy.ui.fragment.home.BadgeSliderUiState
import mobi.worksy.casestudy.ui.fragment.home.PraiseListUiState
import mobi.worksy.casestudy.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val badgeUseCase: BadgeUseCase,
    private val praiseUseCase: PraiseUseCase,
    private val application: WorksyApplication
) : ViewModel() {

    private val _badgeList = MutableLiveData<Resource<BadgeModel>>()
    val badgeList: LiveData<Resource<BadgeModel>>
        get() = _badgeList

    private val _praiseAllList = MutableLiveData<BadgeSliderUiState<List<PraiseItemModel>>>()
    val praiseList: LiveData<BadgeSliderUiState<List<PraiseItemModel>>>
        get() = _praiseAllList

    private val _praisePaginatedList = MutableLiveData<PraiseListUiState<List<PraiseItemModel>>>()
    val praisePaginatedList: LiveData<PraiseListUiState<List<PraiseItemModel>>>
        get() = _praisePaginatedList

    private var currentPage = 1
    private var isLoading = false

    init {
        getBadgeList()
        getAllPraiseData()
        getPraiseList()
    }

    private fun getAllPraiseData() = viewModelScope.launch{
        praiseUseCase.getAllPraiseData().collect { result ->
            when(result){
                is PraiseUseCaseAllResult.Success -> _praiseAllList.value = BadgeSliderUiState.Success(result.praiseAllList)
                is PraiseUseCaseAllResult.Loading -> _praiseAllList.value = BadgeSliderUiState.Loading()
                is PraiseUseCaseAllResult.Error -> _praiseAllList.value = BadgeSliderUiState.Error(result.message)
            }
        }
    }

    private fun getBadgeList() = viewModelScope.launch {
        badgeUseCase().collect { result ->
            when(result){
                is BadgeUseCaseResult.Success -> {
                    _badgeList.value = Resource.Success(result.badgeList)
                    currentPage++
                }
                is BadgeUseCaseResult.Loading -> _badgeList.value = Resource.Loading()
                is BadgeUseCaseResult.Error -> _badgeList.value = Resource.Error(result.message)
            }
        }
    }

    fun calculateBadgeGroups(badgeItems: List<PraiseItemModel>): List<BadgeGroupModel> {
        val groupedBadges = badgeItems.groupBy { it.badgeDetailInformation[0].lookupId to it.badgeDetailInformation[0].lookupValue }
        return groupedBadges.map { (key, items) ->
            val (lookupId, lookupValue) = key
            val itemCount = items.size
            val averagePraiseRating = items.map { it.praiseRating.toFloat() }.average()
            BadgeGroupModel(lookupId, lookupValue, itemCount, averagePraiseRating.toFloat())
        }
    }

    fun calculateBadgeTotalAvg(praiseItems: List<PraiseItemModel>): Pair<Int, Double> {
        val totalRating = praiseItems.sumByDouble { it.praiseRating.toDouble() }
        val numberOfBadges = praiseItems.size
        val averageRating = if (praiseItems.isNotEmpty()) (totalRating / praiseItems.size) else 0.0
        return Pair(numberOfBadges, averageRating)
    }

    fun retryRequest(){
        getAllPraiseData()
        getBadgeList()
        getPraiseList()
    }


    private fun getPraiseList() = viewModelScope.launch {
        praiseUseCase(currentPage).collect { result ->
            when(result){
                is PraiseUseCaseResult.Success -> {
                    isLoading = false
                    val newData = result.praiseList
                    val currentData = _praisePaginatedList.value?.data.orEmpty()
                    val updatedData = currentData.toMutableList().apply { addAll(newData) }
                    _praisePaginatedList.value = PraiseListUiState.Success(updatedData)
                    currentPage++
                }
                is PraiseUseCaseResult.Loading -> {
                    isLoading = true
                    _praisePaginatedList.value = PraiseListUiState.Loading()
                }
                is PraiseUseCaseResult.Error -> {
                    isLoading = false
                    _praisePaginatedList.value = PraiseListUiState.Error(result.message)
                }
            }
        }
    }

    fun onRecyclerViewScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (!isLoading && lastVisibleItemPosition + visibleItemCount >= totalItemCount) {
            getPraiseList()
        }
    }



}