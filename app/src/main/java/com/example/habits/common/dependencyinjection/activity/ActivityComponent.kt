package com.example.habits.common.dependencyinjection.activity

import android.view.LayoutInflater
import com.example.habits.common.dependencyinjection.app.AppComponent
import com.example.habits.networking.StackoverflowApi
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun screensNavigator(): ScreensNavigator
    fun dialogsNavigator(): DialogsNavigator
    fun layoutInflater(): LayoutInflater
    fun stackoverflowApi(): StackoverflowApi
}
