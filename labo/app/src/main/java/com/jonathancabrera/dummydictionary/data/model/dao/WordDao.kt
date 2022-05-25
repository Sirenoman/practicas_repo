package com.jonathancabrera.dummydictionary.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jonathancabrera.dummydictionary.data.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM  word_table")
    fun getAllWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertWord(word: kotlin.collections.List<com.jonathancabrera.dummydictionary.data.model.Word>)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)
}