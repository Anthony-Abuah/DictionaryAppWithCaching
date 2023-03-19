package com.example.dictionarylocaldbcacheapp.feature_dictionary.domain.model

import com.example.dictionarylocaldbcacheapp.feature_dictionary.data.remote.dto.DefinitionDto

data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)
