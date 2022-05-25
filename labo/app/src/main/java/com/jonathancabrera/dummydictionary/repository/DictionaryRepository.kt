package com.jonathancabrera.dummydictionary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jonathancabrera.dummydictionary.data.model.DummyDictionaryDatabase
import com.jonathancabrera.dummydictionary.data.model.Word
import com.jonathancabrera.dummydictionary.data.model.dao.AntonymDao
import com.jonathancabrera.dummydictionary.data.model.dao.SynonymDao
import com.jonathancabrera.dummydictionary.data.model.dao.WordDao
import com.jonathancabrera.dummydictionary.network.ApiResponse
import com.jonathancabrera.dummydictionary.network.WordService
import retrofit2.HttpException
import java.io.IOException

class DictionaryRepository(
    database: DummyDictionaryDatabase,
    private val api: WordService,
){
    private val wordDoa = database.wordDao()

    suspend fun getAllWords(): ApiResponse<LiveData<List<Word>>> {
        return try{
            val response = api.getAllWord()
            //Use Database as cache
            if(response.count > 0){
                wordDoa.insertWord(response.word)
            }
            ApiResponse.Success(data = wordDoa.getAllWords())
        } catch (e: HttpException){
            ApiResponse.Error(exception = e)
        } catch (e: IOException){
            ApiResponse.Error(exception = e)
        }

    }
    suspend fun addWord(word: List<Word>){
        wordDoa.insertWord(word)
    }
}