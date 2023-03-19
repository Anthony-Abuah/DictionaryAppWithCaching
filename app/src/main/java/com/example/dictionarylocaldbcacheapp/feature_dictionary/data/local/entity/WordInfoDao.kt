package com.example.dictionarylocaldbcacheapp.feature_dictionary.data.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionarylocaldbcacheapp.feature_dictionary.domain.model.WordInfo

@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(info: List<WordInfoEntity>)

    @Query ("DELETE FROM WordInfoEntity WHERE word IN(:words)")
    suspend fun deleteWordInfo(words: List<String>)

    @Query ("SELECT * FROM WordInfoEntity WHERE word LIKE '%' || :word || '%' ")
    suspend fun getWordInfo(word: String): List<WordInfoEntity>

}