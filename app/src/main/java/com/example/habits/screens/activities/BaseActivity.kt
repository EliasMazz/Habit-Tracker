package com.example.habits.screens.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.MyApplication
import com.example.habits.common.dependencyinjection.*
import com.example.habits.common.dependencyinjection.activity.ActivityComponent
import com.example.habits.common.dependencyinjection.activity.ActivityModule
import com.example.habits.common.dependencyinjection.activity.DaggerActivityComponent
import com.example.habits.common.dependencyinjection.presentation.DaggerPresentationComponent
import com.example.habits.common.dependencyinjection.presentation.PresentationComponent
import com.example.habits.common.dependencyinjection.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this, appCompositionRoot))
            .build()
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .activityComponent(activityComponent)
            .presentationModule(PresentationModule())
            .build()
    }

    protected val injector: PresentationComponent get() = presentationComponent

}
