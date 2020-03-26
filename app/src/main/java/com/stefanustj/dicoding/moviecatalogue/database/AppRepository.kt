package com.stefanustj.dicoding.moviecatalogue.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.stefanustj.dicoding.moviecatalogue.dao.MovieDao
import com.stefanustj.dicoding.moviecatalogue.dao.TvShowDao
import com.stefanustj.dicoding.moviecatalogue.database.AppDatabase.Companion.getDatabase
import com.stefanustj.dicoding.moviecatalogue.entity.MovieEntity
import com.stefanustj.dicoding.moviecatalogue.entity.TvShowEntity

class AppRepository(context: Context) {

    private var movieDao : MovieDao
    private var tvShowDao : TvShowDao

    private lateinit var allmovie: LiveData<List<MovieEntity>>
    private lateinit var alltvshow: LiveData<List<TvShowEntity>>

    init {
        val db = getDatabase(context)
            movieDao = db.movieDao()
            tvShowDao = db.tvShowDao()
        }

    suspend fun insert(movie: MovieEntity) {
        movieDao.insert(movie)
    }
    suspend fun insert(tvshow: TvShowEntity) {
        tvShowDao.insert(tvshow)
    }
    suspend fun delete(movie: MovieEntity) {
        movieDao.delete(movie)
    }
    suspend fun delete(tvshow: TvShowEntity) {
        tvShowDao.delete(tvshow)
    }
}