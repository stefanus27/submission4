package com.stefanustj.dicoding.moviecatalogue.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.stefanustj.dicoding.moviecatalogue.model.TvShow
import com.stefanustj.dicoding.moviecatalogue.BuildConfig
import org.json.JSONObject
import java.lang.Exception

class ShowViewModel : ViewModel() {
    companion object{
        private val API_KEY: String = BuildConfig.TMDB_API_KEY
    }

    val listShow = MutableLiveData<ArrayList<TvShow>>()

    internal fun setShow() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<TvShow>()
        val url = "https://api.themoviedb.org/3/discover/tv?api_key=$API_KEY&language=en-US"
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
                        val shows = results.getJSONObject(i)
                        val showItems = TvShow()
                        showItems.poster = "https://image.tmdb.org/t/p/w342"+shows.getString("poster_path")
                        showItems.overview = shows.getString("overview")
                        showItems.name = shows.getString("name")
                        showItems.rating = shows.getString("vote_average")
                        showItems.date = shows.getString("first_air_date")
                        listItems.add(showItems)
                    }
                    listShow.postValue(listItems)
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
                listShow.postValue(listItems)
            }
        })
    }

    internal fun getShow() : LiveData<ArrayList<TvShow>> {
        return listShow
    }
}