package com.jonathancabrera.dummydictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonathancabrera.dummydictionary.repository.DictionaryRepository
import com.jonathancabrera.dummydictionary.repository.LoginRepository
import com.jonathancabrera.dummydictionary.ui.word.login.LoginViewModel

class ViewModelFactory<R>(private val repository:R):
    ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                return WordViewModel(repository as DictionaryRepository) as T
            }
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
                return LoginViewModel(repository as LoginRepository)as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
}