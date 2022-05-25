package com.jonathancabrera.dummydictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathancabrera.dummydictionary.data.model.Word
import com.jonathancabrera.dummydictionary.network.ApiResponse
import com.jonathancabrera.dummydictionary.repository.DictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(private val repository: DictionaryRepository): ViewModel() {
    private val _status = MutableLiveData<WordUiState>(WordUiState.Loading)
    val status: LiveData<WordUiState>
        get() = _status


    fun getAllWords() {
        _status.value = WordUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when (val response = repository.getAllWords()) {
                    is ApiResponse.Error -> WordUiState.Error(response.exception)
                    is ApiResponse.Success -> WordUiState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()
                }
            )
        }
    }

    fun addWord(word: List<Word>) {
        viewModelScope.launch {
            repository.addWord(word)
        }
    }


}

