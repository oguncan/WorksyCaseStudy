package mobi.worksy.casestudy.data.model

import com.google.gson.annotations.SerializedName

data class Row(

    @SerializedName("Author")
    val author: List<Author>,
    @SerializedName("Badge")
    val badgeDetailInformation: List<Badge>,
    @SerializedName("Badge.")
    val badgeSingleString: String,
    @SerializedName("Created.")
    val createdDate: String,
    @SerializedName("ID")
    val ID: String,
    @SerializedName("Message")
    val Message: String,
    @SerializedName("PraiseRating")
    val praiseRating: String,
    @SerializedName("RelatedPerson")
    val relatedPerson: List<RelatedPerson>,

)