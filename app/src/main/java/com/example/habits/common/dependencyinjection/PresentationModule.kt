package com.example.habits.common.dependencyinjection

import android.view.LayoutInflater
import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import com.example.habits.screens.common.viewsmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityCompositionRoot: ActivityCompositionRoot) {

    @Provides
    fun stackoverflowApi() = activityCompositionRoot.stackoverflowApi

    @Provides
    fun layoutInflater(): LayoutInflater = activityCompositionRoot.layoutInflater

    @Provides
    fun dialogsNavigator() = activityCompositionRoot.dialogsNavigator

    @Provides
    fun screensNavigator() = activityCompositionRoot.screensNavigator

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun fetchQuestionListUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionListUseCase(stackoverflowApi)

    @Provides
    fun fetchQuestionUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionUseCase(stackoverflowApi)
}
