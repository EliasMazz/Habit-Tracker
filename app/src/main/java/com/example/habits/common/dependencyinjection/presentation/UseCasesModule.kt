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
object UseCasesModule {

    @Provides
    fun fetchQuestionListUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionListUseCase(stackoverflowApi)

    @Provides
    fun fetchQuestionUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionUseCase(stackoverflowApi)
}
