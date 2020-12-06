package com.example.habits.common.composition

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import com.example.habits.screens.common.screens.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi

    val fetchQuestionListUseCase get() = FetchQuestionListUseCase(stackoverflowApi)

    val fetchQuestionUseCase get() = FetchQuestionUseCase(stackoverflowApi)
}
