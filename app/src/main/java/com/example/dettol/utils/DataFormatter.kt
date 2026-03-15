package com.example.dettol.utils

fun formatDate(date: String?): String{

    return try {
        val dateAsList = date?.split("-")?: listOf("0", "0","0")

        val month  = listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )

        "${dateAsList?.get(2) ?:0} ${month[dateAsList?.get(1)?.let { it.toInt()-1 }?:0]} ${dateAsList.get(0)?:0}"

    }catch (e: Exception){
        "NOT KNOWN"
    }

}
