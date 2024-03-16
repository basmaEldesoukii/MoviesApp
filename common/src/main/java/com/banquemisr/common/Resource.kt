package com.banquemisr.common

import java.lang.Exception

sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    class Success<T>(val data: T): Resource<T>()
    class Error(val exception: Exception): Resource<Nothing>()
}