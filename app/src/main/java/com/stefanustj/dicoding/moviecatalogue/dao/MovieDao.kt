package com.stefanustj.dicoding.moviecatalogue.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.stefanustj.dicoding.moviecatalogue.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY name")
    fun getFavMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT COUNT(*) FROM movies WHERE id = :id")
    suspend fun getCountMovie(id: Int): Int?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)


}