package com.example.habits.screens.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.MyApplication
import com.example.habits.common.dependencyinjection.activity.ActivityComponent
import com.example.habits.common.dependencyinjection.activity.ActivityModule
import com.example.habits.common.dependencyinjection.presentation.PresentationComponent

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        appCompositionRoot.newActivityComponentBuilder()
            .activity(this)
            .activityModule(ActivityModule)
            .build()
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()

    }

    protected val injector: PresentationComponent get() = presentationComponent

}
