package com.example.raczakupsecond.utils

import java.util.*

class Utils {

    fun calculateAge(
        birthDay: String
    ) : Int {

        var year: Int = 0
        var month: Int = 0
        var day: Int = 0

        if ("." in birthDay) {
            year = birthDay.split(".")[2].toInt()
            month = birthDay.split(".")[1].toInt()
            day = birthDay.split(".")[0].toInt()
        } else if ("-" in birthDay) {
            year = birthDay.split("-")[0].toInt()
            month = birthDay.split("-")[1].toInt()
            day = birthDay.split("-")[2].toInt()
        }

//        Log.d("BIRTH", "year: $year\nmonth: $month\nday: $day")

        val today = Calendar.getInstance()
//        Log.d("CURRENT", "year: ${today.get(Calendar.YEAR)}\nmonth: ${today.get(Calendar.MONTH) + 1}\nday: ${today.get(Calendar.DAY_OF_MONTH)}")
        var age = today.get(Calendar.YEAR) - year

        if((today.get(Calendar.MONTH) + 1) == month) {
            if (today.get(Calendar.DAY_OF_MONTH) < day) {
                age--
            }
        } else if((today.get(Calendar.MONTH) + 1) < month) {
            age--
        }

        return age
    }

}