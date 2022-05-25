package com.jonathancabrera.dummydictionary.ui.word.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathancabrera.dummydictionary.network.ApiResponse
import com.jonathancabrera.dummydictionary.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository): ViewModel(){
    //    Cajita de entrada para el Login
    val userField = MutableLiveData("")
    val passwordField = MutableLiveData("")
    private val  _status = MutableLiveData<LoginUIStatus>(LoginUIStatus.Resume)
    val status: LiveData<LoginUIStatus>
        get() = _status

    fun onLogin(){
        _status.value = LoginUIStatus.Loading
        viewModelScope.launch (Dispatchers.IO){
            _status.postValue(
                when (val response = repository.login(
                    userField.value.toString(),
                    passwordField.value.toString()
                )){
                    is ApiResponse.Error -> LoginUIStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUIStatus.Resume
                    is ApiResponse.Success -> LoginUIStatus.Success(response.data)
                }
            )
        }
    }
}