package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        if (firstName == "") firstName = null

        return Pair(firstName, lastName)
    }

    fun transliteration(fullName: String?, divider: String = " "): String {
        val parts: List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)?.toLowerCase()
        val lastName = parts?.getOrNull(1)?.toLowerCase()
        if (firstName == "") firstName = null

        val sb = StringBuilder()
        val sb1 = StringBuilder()
        var char: String


        if (!firstName.isNullOrEmpty()) {
            for (ch in firstName) {
                char = transliterate(ch.toString())
                sb.append(char)
            }
            sb[0] = sb[0].toUpperCase()
        }

        if (!lastName.isNullOrEmpty()) {
            for (ch in lastName) {
                char = transliterate(ch.toString())
                sb1.append(char)
            }
            sb1[0] = sb1[0].toUpperCase()
        }

        return "$sb$divider$sb1"

    }

    private fun transliterate(ch: String): String {
        return when (ch) {
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "e"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "i"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "t"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "h"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "sh'"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "yu"
            "я" -> "ya"
            else -> java.lang.String.valueOf(ch)
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val i1: String?
        val i2: String?
        val initials: String?

        if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()) {
            i1 = firstName[0].toUpperCase().toString()
            i2 = lastName[0].toUpperCase().toString()
            initials = i1 + i2
            return initials
        }
        if (!firstName.isNullOrEmpty() && lastName.isNullOrEmpty()) {
            i1 = firstName[0].toUpperCase().toString()
            initials = i1
            return initials
        }
        if (firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()) {
            i2 = lastName[0].toUpperCase().toString()
            initials = i2
        } else return null
        return initials
    }
}