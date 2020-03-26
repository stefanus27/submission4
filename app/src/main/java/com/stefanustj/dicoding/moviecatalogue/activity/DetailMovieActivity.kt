package com.stefanustj.dicoding.moviecatalogue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.stefanustj.dicoding.moviecatalogue.R
import com.stefanustj.dicoding.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*


class DetailMovieActivity : AppCompatActivity() {

    companion object{
        var EXTRA_MOVIES = "extra_movies"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val result = intent.getParcelableExtra(EXTRA_MOVIES) as Movie
        Glide.with(this@DetailMovieActivity).load(result.poster).fitCenter().into(poster_mv)
        name_mv.text = result.name
        date_mv.text = result.date
        rating_mv.text = result.rating
        overview_mv.text = result.overview
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {

                return true
            }
            R.id.action_unfavorite -> {

                return true
            }
            else -> return true
        }
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)

        var favorited = false
        val miAddFav: MenuItem = R.id.action_favorite
        var miDelFav  = R.id.action_unfavorite

        if (favorited) {
            miAddFav.isVisible = false
            miDelFav.isVisible = true
        } else {
            miAddFav.isVisible = true
            miDelFav.isVisible = false
        }
        return true
    }


}
