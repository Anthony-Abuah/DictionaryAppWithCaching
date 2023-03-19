package com.example.dictionarylocaldbcacheapp.feature_dictionary.presentation

import com.example.dictionarylocaldbcacheapp.feature_dictionary.domain.model.WordInfo

data class WordInfoState (
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)