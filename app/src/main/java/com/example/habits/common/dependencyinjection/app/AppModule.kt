package com.example.habits.common.dependencyinjection.app

import android.app.Application
import com.example.habits.Constants
import com.example.habits.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application: Application) {

    @Provides
    fun application() = application

    @Provides
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun getStackoverflowApi(retrofit: Retrofit): StackoverflowApi =
        retrofit.create(StackoverflowApi::class.java)

}
