package com.example.habits.common.dependencyinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    val application get() = appCompositionRoot.application

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val dialogsNavigator by lazy {
        DialogsNavigator(fragmentManager)
    }

    val layoutInflater get() = LayoutInflater.from(activity)

    val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
}
