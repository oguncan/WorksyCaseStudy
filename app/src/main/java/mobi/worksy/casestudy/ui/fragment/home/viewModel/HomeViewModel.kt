package mobi.worksy.casestudy.ui.fragment.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mobi.worksy.casestudy.application.WorksyApplication
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
    val badgeRepository: BadgeRepository,
    val praiseRepository: PraiseRepository,
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
        _badgeList.value = Resource.Loading()
        try {
            val response = badgeRepository.getBadgeList()
            if(response.isSuccessful){
                response.body()?.let { badgeModel ->
                    _badgeList.postValue(Resource.Success(badgeModel))
                }
            }
            else {
                _badgeList.postValue(Resource.Error(response.message()))
            }
        } catch (e: Exception){
            val exceptionMessage = when (e) {
                is IOException -> "Network Failure"
                else -> "Conversion Error"
            }
            _badgeList.postValue(Resource.Error(exceptionMessage))
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
        _praiseList.value = Resource.Loading()
        try {
            val response = praiseRepository.getBadgeList()
            if(response.isSuccessful){
                response.body()?.let { praiseModel ->
                    _praiseList.postValue(Resource.Success(praiseModel))
                }
            }
            else {
                _praiseList.postValue(Resource.Error(response.message()))
            }
        } catch (e: Exception){
            val exceptionMessage = when (e) {
                is IOException -> "Network Failure"
                else -> "Conversion Error"
            }
            _praiseList.postValue(Resource.Error(exceptionMessage))
        }
    }



}