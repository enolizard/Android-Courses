@file:JvmName("TimeUtils")

package by.enolizard.keddit.commons.extensions

import java.util.*

fun Long.getFriendlyTime(): String {
    val dateTime = Date(this * 1000)
    val sb = StringBuffer()
    val current = Calendar.getInstance().time
    var diffInSeconds = ((current.time - dateTime.time) / 1000).toInt()

    val sec = if (diffInSeconds >= 60) diffInSeconds % 60 else diffInSeconds
    diffInSeconds /= 60
    val min = if (diffInSeconds >= 60) diffInSeconds % 60 else diffInSeconds
    diffInSeconds /= 60
    val hrs = if (diffInSeconds > 24) diffInSeconds % 24 else diffInSeconds
    diffInSeconds /= 24
    val days = if (diffInSeconds >= 30) diffInSeconds % 30 else diffInSeconds
    diffInSeconds /= 30
    val months = if (diffInSeconds >= 12) diffInSeconds % 12 else diffInSeconds
    diffInSeconds /= 12
    val years = diffInSeconds

    if (years > 0) {
        sb.append(if (years == 1) "a year" else "$years years")
        if (years <= 6 && months > 0)
            sb.append(if (months == 1) " and a month" else " and $months months")
    } else if (months > 0) {
        sb.append(if (months == 1) "a month" else "$months months")
        if (months <= 6 && days > 0)
            sb.append(if (days == 1) " and a day" else " and $days days")
    } else if (days > 0) {
        sb.append(if (days == 1) "a day" else "$days days")
        if (days <= 3 && hrs > 0)
            sb.append(if (hrs == 1) " and an hour" else " and $hrs hours")
    } else if (hrs > 0) {
        sb.append(if (hrs == 1) "an hour" else "$hrs hours")
        if (min > 1)
            sb.append(" and $min minutes")
    } else if (min > 0) {
        sb.append(if (min == 1) "a minute" else "$min minutes")
        if (sec > 1)
            sb.append(" and $sec seconds")
    } else {
        sb.append(if (sec <= 1) "about a second" else "about $sec seconds")
    }
    sb.append(" ago")

    return sb.toString()
}
