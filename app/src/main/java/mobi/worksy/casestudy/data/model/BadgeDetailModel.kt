package mobi.worksy.casestudy.data.model

import com.google.gson.annotations.SerializedName

data class BadgeDetailModel(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("BadgeIcon")
    val badgeIcon: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("odata.editLink")
    val odataEditLink: String,
    @SerializedName("odata.etag")
    val odataETag: String,
    @SerializedName("odata.id")
    val odataId: String,
    @SerializedName("odata.type")
    val odataType: String
)