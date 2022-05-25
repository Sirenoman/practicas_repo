package com.jonathancabrera.dummydictionary

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jonathancabrera.dummydictionary.data.model.DummyDictionaryDatabase
import com.jonathancabrera.dummydictionary.network.RetrofitInstance
import com.jonathancabrera.dummydictionary.repository.DictionaryRepository
import com.jonathancabrera.dummydictionary.repository.LoginRepository

class DummyDictionaryApplication : Application() {
    private val prefs: SharedPreferences by lazy{
        getSharedPreferences("DummyDictionary", Context.MODE_PRIVATE)
    }
    private val dataBase by lazy{
        DummyDictionaryDatabase.getInstance(this)
    }

    private fun getApiService() = with(RetrofitInstance){
        setToken(getToken())
        getWordService()
    }
    fun getDictionaryRepository() =
        DictionaryRepository(dataBase, getApiService())

    fun getLoginRepository()=
        LoginRepository(getApiService())

    private fun getToken(): String = prefs.getString(USER_TOKEN, "")!!

    fun isUserLogin() = getToken() !=""

    fun saveAuthToken(token:String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }
    companion object{
        const val USER_TOKEN = "user_token"
    }
}