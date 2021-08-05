package com.demo.application.ui.popular

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.spacex.data.remote.NewsService
import com.demo.application.data.models.PopularNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewModel  @ViewModelInject constructor(
  val newsService: NewsService
) : ViewModel() {

  private var _newsData = MutableLiveData<PopularNews?>()
  val newsData: MutableLiveData<PopularNews?>
    get() = _newsData

  init {
    _newsData.value = null
     getNewsData()
  }

  fun getNewsData() {
    val api = newsService.getPopularNews()

    api.enqueue(object : Callback<PopularNews> {
      override fun onFailure(call: Call<PopularNews>, t: Throwable) {
        Log.d("getPopularNews", "Failed :" + t.message)
      }

      override fun onResponse(call: Call<PopularNews>, response: Response<PopularNews>) {
        Log.d("getPopularNews", "Success :")
        _newsData.value = response.body()
      }
    })
  }
}
