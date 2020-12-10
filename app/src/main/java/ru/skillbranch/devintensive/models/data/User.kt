package ru.skillbranch.devintensive.models.data


import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {


    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")


    fun printMe() = println(
        """
                 id: $id
                 firstName: $firstName
                 lastName: $lastName
                 avatar: $avatar
                 rating: $rating
                 respect: $respect
                 lastVisit: $lastVisit
                 isOnline: $isOnline
        """.trimIndent()
    )

    fun toUserItems(): UserItem {
        val lastActivity = when{
            lastVisit == null -> "Еще ни разу не заходил"
            isOnline -> "Онлайн"
            else -> "Последний раз в сети ${lastVisit.humanizeDiff()}"
        }

        return UserItem(
            id,
            "${firstName.orEmpty()} ${lastName.orEmpty()}",
            Utils.toInitials(firstName, lastName),
            avatar,
            lastActivity,
            false,
            isOnline
        )
    }

    companion object Factory {
        private var lastId = -1

        fun makeUser(fullName: String?): User {
            lastId++

            var firstName:String? ="Ошибка"
            var lastName: String? ="Ошибка"
            val parts: List<String>?
            if (!fullName.isNullOrEmpty() ) {
                parts = fullName.split(" ")
                firstName = parts.getOrNull(0)
                lastName = parts.getOrNull(1)
            }
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}