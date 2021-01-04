package com.strangecoder.itunes_api.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.strangecoder.itunes_api.db.entities.RoomDbItem
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ResultDaoTest {
    private lateinit var dao: ResultDao
    private lateinit var db: ResultDatabase

    @Before
    fun initDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ResultDatabase::class.java).build()
        dao = db.resultDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAllItems() {
        val dbItem = RoomDbItem(
            trackId = 11,
            artistId = 23,
            artistName = "artistName",
            artistViewUrl = "artistViewUrl",
            artworkUrl100 = "artworkUrl100",
            artworkUrl600 = "artworkUrl600",
            country = "country",
            isStreamable = true,
            previewUrl = "previewUrl",
            primaryGenreName = "primaryGenreName",
            releaseDate = "releaseDate",
            trackName = "trackName",
            trackNumber = 12,
            trackTimeMillis = 1294801,
            trackViewUrl = "trackViewUrl"
        )
        val list = listOf(dbItem)
        dao.insertAll(list)
        val dbListItems = dao.getAll()
        assertThat(dbListItems).contains(dbItem)
    }
}