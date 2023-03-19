package com.example.dictionarylocaldbcacheapp.feature_dictionary.domain.repository

import com.example.dictionarylocaldbcacheapp.core.util.Resource
import com.example.dictionarylocaldbcacheapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}