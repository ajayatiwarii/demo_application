package com.app.spacex.di

import com.app.spacex.data.remote.NewsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

  @Singleton
  @Provides
  fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
  .baseUrl("https://api.nytimes.com/")
  .addConverterFactory(GsonConverterFactory.create(gson))
  .build()

  @Provides
  fun provideGson():Gson = GsonBuilder().create()

  @Provides
  fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)


}
