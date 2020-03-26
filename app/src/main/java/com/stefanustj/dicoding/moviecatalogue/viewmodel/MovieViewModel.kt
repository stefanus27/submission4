package com.stefanustj.dicoding.moviecatalogue.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stefanustj.dicoding.moviecatalogue.model.Movie
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.stefanustj.dicoding.moviecatalogue.BuildConfig
import org.json.JSONObject
import java.lang.Exception


class MovieViewModel : ViewModel(){
    companion object{
        private val API_KEY: String = BuildConfig.TMDB_API_KEY
    }

    val listMovie = MutableLiveData<ArrayList<Movie>>()

    internal fun setMovie() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<Movie>()
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$API_KEY&language=en-US"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val results = responseObject.getJSONArray("results")

                    for(i in 0 until results.length()){
                        val movie = results.getJSONObject(i)
                        val movieItems = Movie()
                        movieItems.poster = "https://image.tmdb.org/t/p/w342"+movie.getString("poster_path")
                        movieItems.overview = movie.getString("overview")
                        movieItems.name = movie.getString("title")
                        movieItems.rating = movie.getString("vote_average")
                        movieItems.date = movie.getString("release_date")
                        listItems.add(movieItems)
                    }
                    listMovie.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
                listMovie.postValue(listItems)
            }
        })
    }

    internal fun getMovie() : LiveData<ArrayList<Movie>> {
        return listMovie
    }
}