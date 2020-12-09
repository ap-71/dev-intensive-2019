package ru.skillbranch.devintensive.extensions

fun String.truncate(value : Int = 16):String{
    return if (this.trim().length>=value) this.removeRange(value,length).trim()+"..." else this.trim()
}