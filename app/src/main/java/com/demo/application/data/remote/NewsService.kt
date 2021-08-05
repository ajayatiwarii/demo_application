package com.app.spacex.data.remote


import androidx.lifecycle.LiveData
import com.demo.application.data.models.PopularNews
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {
  //https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=XAQwH6Xyl8giVdXktn5enwY5AZZArGnY
  @GET("svc/mostpopular/v2/viewed/1.json?api-key=XAQwH6Xyl8giVdXktn5enwY5AZZArGnY")
  fun getPopularNews() : Call<PopularNews>

}
