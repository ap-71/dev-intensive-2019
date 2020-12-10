package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?> {
        val parse: List<String>? = fullName?.split(" ")
        val firstName = form(parse?.getOrNull(0))
        val lastName = form(parse?.getOrNull(1))
        return firstName to lastName
    }
    private fun form(value:String?): String? {
        return when (value) {
            "" -> null
            " " -> null
            else -> value
        }
    }
}