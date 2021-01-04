package com.strangecoder.itunes_api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.strangecoder.itunes_api.db.entities.RoomDbItem

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(resultItems: List<RoomDbItem>)

    @Query("SELECT * FROM resultItems WHERE trackName LIKE :searchTerm OR artistName LIKE :searchTerm")
    fun getLocalResults(searchTerm: String): List<RoomDbItem>

    @Query("SELECT * FROM resultItems")
    fun getAll(): List<RoomDbItem>
}