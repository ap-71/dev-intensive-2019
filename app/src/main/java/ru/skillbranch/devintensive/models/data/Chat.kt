package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.utils.DataGenerator
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

 data class Chat (
    val id:String,
    val title:String,
    val members : List<User> = listOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
var isArchived: Boolean = false
){
    private fun lastMessageDate():Date?{
       val asd = DataGenerator.stabChats[id.toInt()].messages
        return if (asd.isNotEmpty()) asd.last().date else null
    }

     private fun lastMessageShort():Pair<String,String>{
val asd = DataGenerator.stabChats[id.toInt()].messages
         return if (asd.isNotEmpty()) {
             "${if (asd.isNotEmpty()) asd.last() else " "}" to "${DataGenerator.stabUsers[members.last().id.toInt()].firstName}"
         }else "Нет сообщений" to "${DataGenerator.stabUsers[members.last().id.toInt()].firstName}"

     }

    private fun unreadableMessageCount():Int{

        return DataGenerator.stabChats[id.toInt()].messages.lastIndex
    }

    private fun isSingle():Boolean = members.size == 1

    fun toChatItem(): ChatItem {
        return if (isSingle()){
            val user = members.first()
            ChatItem(
                id,
                user.avatar,
                Utils.toInitials(user.firstName,user.lastName) ?: "??",
                "${user.firstName?:""} ${user.lastName?:""}",
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                user.isOnline
            )
        }else{
            ChatItem(
                id,
                null,
                "",
                title,
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                false,
                ChatType.GROUP,
                lastMessageShort().second
            )
        }
    }



}

enum class ChatType{
    SINGLE,
    GROUP,
    ARCHIVE
}