package com.example.habits.screens.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.MyApplication
import com.example.habits.common.composition.ActivityCompositionRoot
import com.example.habits.common.composition.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }


    protected val compositionRoot by lazy { PresentationCompositionRoot(activityCompositionRoot) }

}
