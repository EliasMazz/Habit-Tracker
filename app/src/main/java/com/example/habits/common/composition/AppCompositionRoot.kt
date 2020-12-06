package com.example.habits.common.composition

import com.example.habits.Constants
import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val stackOverflowApi = retrofit.create(StackoverflowApi::class.java)

    val fetchQuestionListUseCase get() = FetchQuestionListUseCase(stackOverflowApi)

    val fetchQuestionUseCase get() = FetchQuestionUseCase(stackOverflowApi)
}
