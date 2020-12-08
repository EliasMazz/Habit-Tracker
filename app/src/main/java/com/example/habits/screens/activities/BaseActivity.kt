package com.example.habits.screens.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.MyApplication
import com.example.habits.common.dependencyinjection.ActivityCompositionRoot
import com.example.habits.common.dependencyinjection.Injector
import com.example.habits.common.dependencyinjection.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val compositionRoot by lazy { PresentationCompositionRoot(activityCompositionRoot) }


    protected  val injector get() = Injector(compositionRoot)

}
