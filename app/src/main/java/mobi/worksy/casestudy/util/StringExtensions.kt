package mobi.worksy.casestudy.util

fun Double.formatOneFloatNumber(): String {
    return String.format("%.1f", this)
}

fun String.formatToComma(): String = this.replace('.', ',')