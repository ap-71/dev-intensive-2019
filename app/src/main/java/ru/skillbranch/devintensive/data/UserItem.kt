package ru.skillbranch.devintensive.data

data class UserItem(
val id: String,
val fullName:String,
val initials: String?,
val avatar: String?,
var lastActivity:String,
var isSelected: Boolean = false,
var isOnline: Boolean = false
)
