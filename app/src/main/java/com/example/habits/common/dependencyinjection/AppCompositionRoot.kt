package com.example.habits.common.dependencyinjection

import android.app.Application
import androidx.annotation.UiThread
import com.example.habits.Constants
import com.example.habits.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@UiThread
class AppCompositionRoot(val application: Application) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

  


}
