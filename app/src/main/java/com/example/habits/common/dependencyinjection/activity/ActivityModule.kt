package com.example.habits.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.habits.common.dependencyinjection.app.AppComponent
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: AppCompatActivity,
    private val appComponent: AppComponent
) {
    @Provides
    fun activity() = activity

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun application() = appComponent.application()

    @Provides
    fun screensNavigator(activity: AppCompatActivity): ScreensNavigator = ScreensNavigator(activity)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)

    @Provides
    fun stackoverflowApi() = appComponent.getStackoverflowApi()
}
