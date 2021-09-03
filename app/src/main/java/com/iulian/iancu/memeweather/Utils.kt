package com.iulian.iancu.memeweather

import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDateFromUTC(epoch: Long): String {
    val sdf = SimpleDateFormat("dd, MMM yyyy hh:mm:ss a", Locale.getDefault())
    return sdf.format(Date(epoch*1000))
}

fun getWatherIconFromCode(weatherId: Int): String {
    return when (weatherId) {
        200, 201, 202, 210, 211, 212, 221, 230, 231, 232 -> THUNDERSTORM_GIF
        300, 301, 302, 310, 311, 312, 313, 314, 321 -> DRIZZLE_GIF
        500, 501, 502, 503, 504, 511, 520, 521, 522, 531 -> RAIN_GIF
        600, 601, 602, 611, 612, 613, 615, 616, 620, 621, 622 -> SNOW_GIF
        701, 721, 741 -> MIST_GIF
        711, 761 -> SMOKE_GIF
        731, 751 -> SAND_GIF
        762 -> VOLCANO_GIF
        771, 781 -> TORNADO_GIF
        else -> CLEAR_GIF
    }
}