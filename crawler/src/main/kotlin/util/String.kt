package util

fun extractNumber(string: String) =
    string.filter { it.isDigit() }.toInt()

