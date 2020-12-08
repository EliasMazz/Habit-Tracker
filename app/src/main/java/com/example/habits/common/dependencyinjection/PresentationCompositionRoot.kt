package com.example.habits.common.dependencyinjection

import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import com.example.habits.screens.common.viewsmvc.ViewMvcFactory

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    private val stackoverflowApi get() = activityCompositionRoot.stackoverflowApi

    private val layoutInflater get() = activityCompositionRoot.layoutInflater

    val dialogsNavigator get() = activityCompositionRoot.dialogsNavigator

    val screensNavigator get() = activityCompositionRoot.screensNavigator

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)

    val fetchQuestionListUseCase get() = FetchQuestionListUseCase(stackoverflowApi)

    val fetchQuestionUseCase get() = FetchQuestionUseCase(stackoverflowApi)
}
