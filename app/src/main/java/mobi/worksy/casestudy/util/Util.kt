package mobi.worksy.casestudy.util

import android.view.View
import mobi.worksy.casestudy.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}


fun parseDate(format: String, dateString: String): Date {
    return SimpleDateFormat(format, Locale.getDefault()).parse(dateString) ?: Date()
}
fun calculateDayDifference(date1: Date, date2: Date): Long {
    val calDate1 = Calendar.getInstance().apply { time = date1 }
    val calDate2 = Calendar.getInstance().apply { time = date2 }
    calDate1.set(Calendar.HOUR_OF_DAY, 0)
    calDate1.set(Calendar.MINUTE, 0)
    calDate1.set(Calendar.SECOND, 0)
    calDate1.set(Calendar.MILLISECOND, 0)

    calDate2.set(Calendar.HOUR_OF_DAY, 0)
    calDate2.set(Calendar.MINUTE, 0)
    calDate2.set(Calendar.SECOND, 0)
    calDate2.set(Calendar.MILLISECOND, 0)

    val timeDifference = calDate2.timeInMillis - calDate1.timeInMillis
    return timeDifference / (1000 * 60 * 60 * 24)
}

fun formatDate(date: Date, format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(date)
}