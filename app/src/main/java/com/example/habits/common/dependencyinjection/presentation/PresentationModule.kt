package com.example.habits.common.dependencyinjection.presentation

import android.view.LayoutInflater
import com.example.habits.common.dependencyinjection.activity.ActivityComponent
import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import com.example.habits.screens.common.viewsmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityComponent: ActivityComponent) {

    @Provides
    fun stackoverflowApi() = activityComponent.stackoverflowApi()

    @Provides
    fun layoutInflater(): LayoutInflater = activityComponent.layoutInflater()

    @Provides
    fun dialogsNavigator() = activityComponent.dialogsNavigator()

    @Provides
    fun screensNavigator() = activityComponent.screensNavigator()

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun fetchQuestionListUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionListUseCase(stackoverflowApi)

    @Provides
    fun fetchQuestionUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionUseCase(stackoverflowApi)
}
