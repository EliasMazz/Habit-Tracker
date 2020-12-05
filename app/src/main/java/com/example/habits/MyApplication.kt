package com.example.habits

import android.app.Application
import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val stackOverflowApi = retrofit.create(StackoverflowApi::class.java)

    val fetchQuestionListUseCase get() = FetchQuestionListUseCase(stackOverflowApi)

    val fetchQuestionUseCase get() = FetchQuestionUseCase(stackOverflowApi)

    override fun onCreate() {
        super.onCreate()


    }
}
