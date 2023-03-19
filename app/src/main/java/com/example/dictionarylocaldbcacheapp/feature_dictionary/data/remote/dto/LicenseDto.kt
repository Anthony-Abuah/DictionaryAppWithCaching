package com.example.dictionarylocaldbcacheapp.feature_dictionary.data.remote.dto

import com.example.dictionarylocaldbcacheapp.feature_dictionary.domain.model.License

data class LicenseDto(
    val name: String,
    val url: String
){
    fun toLicence(): License{
        return License(
            name = name,
            url = url
        )
    }
}