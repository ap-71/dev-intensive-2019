package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.difference(): String? {
    val differenceTime = Date()
    differenceTime.time = Date().time - this.time
    return when(differenceTime.time){
        in 0*SECOND..1*SECOND -> "только что"
        in 1*SECOND..45*SECOND -> "несколько секунд назад"
        in 45*SECOND..75*SECOND -> "минуту назад"
        in 75*SECOND..45*MINUTE -> "${differenceTime.format("m")} ${
            when(differenceTime.format("m").toInt()){
                in 2..4 -> "минуты"
                in 22..24 -> "минуты"
                in 32..34 -> "минуты"
                in 42..44 -> "минуты"
                in 52..54 -> "минуты"
                in 5..20 -> "минут"
                in 25..30 -> "минут"
                in 35..40 -> "минут"
                in 45..50 -> "минут"
                in 55..59 -> "минут"
                else -> "минуту"
            }
        } назад"
        in 45*MINUTE..75*MINUTE -> "час назад"
        in 75*MINUTE..22*HOUR ->  "${differenceTime.time/HOUR} ${
            when(differenceTime.time/HOUR){
                in 2..4 -> "часа"
                in 22..24 -> "часа"
                in 5..20 -> "часов"
                else -> "час"
            }
        } назад"
        in 22*HOUR..26*HOUR -> "день назад"
        in 26*HOUR..360*DAY -> "${differenceTime.time/ DAY} ${
            when(differenceTime.time/ DAY){
                in 2..4 -> "дня"
                in 22..24 -> "дня"
                in 32..34 -> "дня"
                in 42..44 -> "дня"
                in 52..54 -> "дня"
                in 5..20 -> "дней"
                in 25..30 -> "дней"
                in 35..40 -> "дней"
                in 45..50 -> "дней"
                in 55..59 -> "дней"
                else -> "день"
            }
        } назад"
        else -> "более года назад"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}