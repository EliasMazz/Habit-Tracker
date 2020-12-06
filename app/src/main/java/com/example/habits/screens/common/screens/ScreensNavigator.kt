package com.example.habits.screens.common.screens

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val activity: AppCompatActivity) {

    fun startQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(activity, questionId)
    }

    fun navigateBack() {
        activity.onBackPressed()
    }
}
