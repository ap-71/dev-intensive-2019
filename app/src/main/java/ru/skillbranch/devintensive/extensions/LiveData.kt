package ru.skillbranch.devintensive.extensions

import androidx.lifecycle.MutableLiveData

fun <T> mutableLiveData(defaultVale: T? = null): MutableLiveData<T>{
    val data = MutableLiveData<T>()

    if (defaultVale!=null){
        data.value = defaultVale

    }
    return data
}