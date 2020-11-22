package com.example.habits.networking

import com.example.habits.screens.model.Question
import com.google.gson.annotations.SerializedName

data class QuestionsListResponseSchema (@SerializedName("items") val questions: List<Question>)
