package com.rc.base.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun todayDateString(dateFormat:String):String{
    val now = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
    return dateFormatter.format(now)
}