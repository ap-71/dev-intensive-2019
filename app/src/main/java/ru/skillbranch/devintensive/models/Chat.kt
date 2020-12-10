package ru.skillbranch.devintensive.models

class Chat (
        val id: String,
        val members: MutableList<UserView> = mutableListOf(),
        val message: MutableList<BaseMessage> = mutableListOf()
){
}