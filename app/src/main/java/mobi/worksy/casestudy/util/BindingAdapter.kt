package mobi.worksy.casestudy.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import mobi.worksy.casestudy.R

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