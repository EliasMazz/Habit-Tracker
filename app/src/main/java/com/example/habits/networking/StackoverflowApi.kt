package com.example.habits.networking

import com.example.habits.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StackoverflowApi {

    @GET("/questions?key=" + Constants.STACKOVERFLOW_API_KEY + "&order=desc&sort=activity&site=stackoverflow")
    suspend fun lastActiveQuestions(@Query("pagesize") pageSize: Int?): Response<QuestionsListResponseSchema>
}
