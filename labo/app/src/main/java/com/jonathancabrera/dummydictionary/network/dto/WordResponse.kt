package com.jonathancabrera.dummydictionary.network.dto


import com.google.gson.annotations.SerializedName
import com.jonathancabrera.dummydictionary.data.model.Word

data class WordResponse(
    @SerializedName("count")
    val count:Int,
    @SerializedName("word")
    val word:List<Word>
)
