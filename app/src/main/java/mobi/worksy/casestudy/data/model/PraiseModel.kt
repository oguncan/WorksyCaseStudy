package mobi.worksy.casestudy.data.model

import com.google.gson.annotations.SerializedName

data class PraiseModel(
    @SerializedName("Row")
    val praiseItem: List<PraiseItemModel>
)