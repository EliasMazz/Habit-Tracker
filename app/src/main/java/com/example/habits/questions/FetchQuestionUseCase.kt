package com.example.habits.questions

import com.example.habits.Constants
import com.example.habits.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchQuestionUseCase {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val stackOverflowApi = retrofit.create(StackoverflowApi::class.java)

    sealed class Result {
        class Sucess(val body: String) : Result()
        object Failure : Result()
    }

    suspend fun fetchQuestionDetails(questionId: String) =
        withContext(Dispatchers.IO) {
            try {
                val response = stackOverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Sucess(response.body()!!.question.body)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
}
