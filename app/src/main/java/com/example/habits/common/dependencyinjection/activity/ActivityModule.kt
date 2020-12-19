package com.example.habits.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

    @ActivityScope
    @Provides
    fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

    @ActivityScope
    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)
}
