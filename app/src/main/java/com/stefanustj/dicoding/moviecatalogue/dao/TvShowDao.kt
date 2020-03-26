package com.stefanustj.dicoding.moviecatalogue.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.stefanustj.dicoding.moviecatalogue.entity.TvShowEntity


@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_shows ORDER BY name")
    fun getFavTvShow(): LiveData<List<TvShowEntity>>

    @Query("SELECT COUNT(*) FROM movies WHERE id = :id")
    suspend fun getCountTvShow(id: Int): Int?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tvshow: TvShowEntity)

    @Delete
    suspend fun delete(tvshow: TvShowEntity)
}