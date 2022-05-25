package com.jonathancabrera.dummydictionary.ui.word.login

import java.lang.Exception

sealed class  LoginUIStatus{
    object Resume :LoginUIStatus()
    object Loading :LoginUIStatus()
    class Error(val exception: Exception):LoginUIStatus()
    data class Success(val token:String):LoginUIStatus()
}