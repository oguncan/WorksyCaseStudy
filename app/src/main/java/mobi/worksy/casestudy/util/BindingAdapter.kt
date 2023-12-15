package mobi.worksy.casestudy.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import mobi.worksy.casestudy.R
import java.util.Date
import kotlin.random.Random

@BindingAdapter("loadImageWithLookupId")
fun ImageView.loadImageWithLookupId(lookupId: Int?) {
    lookupId?.let {
        var drawableName = "w$lookupId"
        val drawableResId = context.resources.getIdentifier(drawableName, "drawable", context.packageName)
        Glide.with(this)
            .load(ContextCompat.getDrawable(this.context, drawableResId))
            .centerCrop()
            .into(this)
    }
}

@BindingAdapter("randomDrawable")
fun ImageView.setRandomDrawable(value: Any?) {
    val drawableResId = if (Random.nextBoolean()) {
        R.drawable.ic_author_face_1
    } else {
        R.drawable.ic_author_face_2
    }
    Glide.with(this)
        .load(ContextCompat.getDrawable(this.context, drawableResId))
        .centerCrop()
        .into(this)
}

@BindingAdapter("loadImageWithBadgeString")
fun ImageView.loadImageWithBadgeString(badgeString: String?) {
    badgeString?.let {
        val splitText = it.split(";#")
        if(splitText.isNotEmpty()){
            var drawableName = "w${splitText[0]}"
            val drawableResId = context.resources.getIdentifier(drawableName, "drawable", context.packageName)
            Glide.with(this)
                .load(ContextCompat.getDrawable(this.context, drawableResId))
                .centerCrop()
                .into(this)
        }
    }
}

@BindingAdapter("setTextWithBadgeString")
fun TextView.setTextWithBadgeString(badgeString: String?) {
    badgeString?.let {
        val splitText = it.split(";#")
        if(splitText.isNotEmpty()){
            text = splitText[1]
        }
    }
}

@BindingAdapter("formatCreatedDate")
fun TextView.formatCreatedDate(createdDate: String?) {
    createdDate?.let {
        //Şu an ki format
        val oldFormat = "MM/dd/yyyy hh:mm a"
        val newFormat = "HH:mm'\''da Gönderdi'"


        val oldDate = parseDate(oldFormat, createdDate)
        val currentDate = Date()

        val dayDifference = calculateDayDifference(oldDate, currentDate)

        val result = when {
            dayDifference == 0L -> "Bugün, ${formatDate(oldDate, newFormat)}"
            dayDifference == 1L -> "Dün, ${formatDate(oldDate, newFormat)}"
            else -> "${formatDate(oldDate, "dd/MM/yyyy")} ${formatDate(oldDate, newFormat)}"
        }

        text = result
    }
}