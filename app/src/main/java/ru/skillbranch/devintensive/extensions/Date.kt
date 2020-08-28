package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits) : Date {
    this.time  += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    return this
}

fun Date.humanizeDiff(fromDate: Date = Date()) : String {
    val from = fromDate.time
    val to = this.time

    val elapsed = from - to
    if (elapsed in 0..1*SECOND) return "только что"
    if (elapsed in 1*SECOND..45*SECOND) return "несколько секунд назад"
    if (elapsed in 45*SECOND..75*SECOND) return "минуту назад"
    if (elapsed in 75*SECOND..45*MINUTE) return "${elapsed / MINUTE} ${if (((elapsed / MINUTE) % 10) in 2L..4L) "минуты" else "минут"} назад"
    if (elapsed in 45*MINUTE..75*MINUTE) return "час назад"
    if (elapsed in 75* MINUTE..22*HOUR) return "${elapsed / HOUR} ${if (((elapsed / HOUR) % 10) in 2L..4L) "часа" else "часов"} назад"
    if (elapsed in 22*HOUR..26*HOUR) return "день назад"
    if (elapsed in 26*HOUR..360*DAY) return "${elapsed / DAY} ${if (((elapsed / DAY) % 10) in 2L..4L) "дня" else "дней"} назад"
    if (elapsed > 360*DAY) return "более года назад"

    val future = to - from
    if (future in 45*SECOND..75*SECOND) return "через минуту"
    if (future in 75*SECOND..45*MINUTE) return "через ${future / MINUTE} ${if (((future / MINUTE) % 10) in 2L..4L) "минуты" else "минут"}"
    if (future in 45*MINUTE..75*MINUTE) return "через час"
    if (future in 75* MINUTE..22*HOUR) return "через ${future / HOUR} ${if (((future / HOUR) % 10) in 2L..4L) "часа" else "часов"}"
    if (future in 22*HOUR..26*HOUR) return "через день"
    if (future in 26*HOUR..360*DAY) return "через ${future / DAY} ${if (((future / DAY) % 10) in 2L..4L) "дня" else "дней"}"
    if (future > 360*DAY) return "более чем через год"

    return ""
}

enum class TimeUnits {

    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return when (this) {
            SECOND -> "$value ${if (value % 10 == 1) "секунду" else if (value % 10 in 2..4) "секунды" else "секунд"}"
            MINUTE -> "$value ${if (value % 10 == 1) "минуту" else if (value % 10 in 2..4) "минуты" else "минут"}"
            HOUR -> "$value ${if (value % 10 == 1) "час" else if (value % 10 in 2..4) "часа" else "часов"}"
            DAY -> "$value ${if (value % 10 == 1) "день" else if (value % 10 in 2..4) "дня" else "дней"}"
        }
    }

}