package com.example.habits.questions

import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.model.Question
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchQuestionListUseCase(private val stackoverflowApi: StackoverflowApi) {

    sealed class Result {
        class Success(val questions: List<Question>) : Result()
        object Failure : Result()
    }

    suspend fun fetchLatestQuestions() =
        withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.lastActiveQuestions(20)
                if (response.isSuccessful && response.body()!!.questions != null) {
                    return@withContext Result.Success(response.body()!!.questions)
                } else {
                    return@withContext Result.Failure
                }
            } catch (throwable: Throwable) {
                if (throwable !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw throwable
                }
            }
        }
}
