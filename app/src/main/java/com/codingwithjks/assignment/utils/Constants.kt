package com.codingwithjks.assignment.utils

import android.content.Context
import android.widget.Toast
import java.util.*

fun Context.showMsg(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}


fun currentTime(username: String): String {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    if (currentHour < 12)
        return "Good morning, $username"
    else if (currentHour < 17)
        return "Good Afternoon, $username"
    else return "Good Evening $username"
}
