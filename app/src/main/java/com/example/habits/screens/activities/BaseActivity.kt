package com.example.habits.screens.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.MyApplication
import com.example.habits.common.dependencyinjection.activity.ActivityComponent
import com.example.habits.common.dependencyinjection.activity.ActivityModule
import com.example.habits.common.dependencyinjection.presentation.PresentationComponent
import com.example.habits.common.dependencyinjection.presentation.PresentationModule
import com.example.habits.common.dependencyinjection.presentation.UseCasesModule

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        appCompositionRoot.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()

    }

    protected val injector: PresentationComponent get() = presentationComponent

}
