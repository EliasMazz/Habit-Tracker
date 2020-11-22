package com.example.habits.networking

import com.example.habits.screens.model.QuestionWithBody
import com.google.gson.annotations.SerializedName

data class SingleQuestionReponseSchema(
    @SerializedName("items") val questions: List<QuestionWithBody>
) {
    val question: QuestionWithBody get() = questions[0]
}


