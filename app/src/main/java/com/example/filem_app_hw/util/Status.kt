package com.example.filem_app_hw.util

sealed class Status<out T>{
    object Loading : Status<Nothing>()
    data class Error(val message: String) : Status<Nothing>()
    data class Success<T>(val data: T): Status<T>()

    fun toData(): T? = if(this is Success) data else null
}


