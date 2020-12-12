package com.example.habits.common.dependencyinjection

import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import com.example.habits.screens.common.viewsmvc.ViewMvcFactory
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun viewMvcFactory(): ViewMvcFactory
    fun fetchQuestionListUseCase(): FetchQuestionListUseCase
    fun fetchQuestionUseCase(): FetchQuestionUseCase
    fun dialogsNavigator(): DialogsNavigator
    fun screensNavigator(): ScreensNavigator
}
