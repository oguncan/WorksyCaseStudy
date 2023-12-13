package mobi.worksy.casestudy.ui.fragment.home.viewModel

import android.util.Log
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
import mobi.worksy.casestudy.data.domain.PraiseUseCaseResult
import mobi.worksy.casestudy.data.model.BadgeGroupModel
import mobi.worksy.casestudy.data.model.BadgeModel
import mobi.worksy.casestudy.data.model.PraiseModel
import mobi.worksy.casestudy.data.model.Row
import mobi.worksy.casestudy.data.repository.BadgeRepository
import mobi.worksy.casestudy.data.repository.PraiseRepository
import mobi.worksy.casestudy.util.Resource
import java.io.IOException
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

    private val _praiseList = MutableLiveData<Resource<PraiseModel>>()
    val praiseList: LiveData<Resource<PraiseModel>>
        get() = _praiseList

    init {
        getBadgeList()
        getPraiseList()
    }

    fun getBadgeList() = viewModelScope.launch {
        badgeUseCase().collect { result ->
            when(result){
                is BadgeUseCaseResult.Success -> _badgeList.value = Resource.Success(result.badgeList)
                is BadgeUseCaseResult.Loading -> _badgeList.value = Resource.Loading()
                is BadgeUseCaseResult.Error -> _badgeList.value = Resource.Error(result.message)
            }
        }
    }

    fun calculateBadgeGroups(badgeItems: List<Row>): List<BadgeGroupModel> {
        val groupedBadges = badgeItems.groupBy { it.badgeDetailInformation[0].lookupId to it.badgeDetailInformation[0].lookupValue }
        return groupedBadges.map { (key, items) ->
            val (lookupId, lookupValue) = key
            val itemCount = items.size
            val averagePraiseRating = items.map { it.praiseRating.toFloat() }.average()
            BadgeGroupModel(lookupId, lookupValue, itemCount, averagePraiseRating.toFloat())
        }
    }

    fun calculateBadgeTotalAvg(rows: List<Row>): Pair<Double, Double> {
        val totalRating = rows.sumByDouble { it.praiseRating.toDouble() }
        val averageRating = if (rows.isNotEmpty()) (totalRating / rows.size) else 0.0
        return Pair(totalRating, averageRating)
    }



    fun getPraiseList() = viewModelScope.launch {
        praiseUseCase().collect { result ->
            when(result){
                is PraiseUseCaseResult.Success -> _praiseList.value = Resource.Success(result.praiseList)
                is PraiseUseCaseResult.Loading -> _praiseList.value = Resource.Loading()
                is PraiseUseCaseResult.Error -> _praiseList.value = Resource.Error(result.message)
            }
        }
    }



}