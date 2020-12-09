package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECONDS = 1000L
const val MINUTE = 60 * SECONDS
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.shortFormat():String{
   val pattern = if (isSameDay(Date())) "HH:mm" else "dd.MM.yy"
    val dateFormat= SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.isSameDay(date: Date):Boolean{
    val day1 = this.time / DAY
    val day2 = date.time / DAY
    return  day1 == day2
}

fun Date.add(value: Int, timeUnits: TimeUnits): Date {
    var time = this.time

    time += when (timeUnits) {
        TimeUnits.SECOND -> value * SECONDS
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val dateDiff = (date.time - this.time) / 1000

    return when (dateDiff) {
        in 0..1 -> "Только что"
        in 1..45 -> "Несколько секунд назад"
        in 45..75 -> "Минуту назад"
        in 75..2700 -> "${dateDiff / 60} минут назад"
        in 2700..4500 -> "Час назад"
        in 4500..79200 -> "${(dateDiff / 60) / 60} часов назад"
        in 79200..93600 -> "День назад"
        in 93600..31104000 -> "${((dateDiff / 60) / 60) / 24} дней назад "
        else -> "Более года назад"
    }


}


enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}
