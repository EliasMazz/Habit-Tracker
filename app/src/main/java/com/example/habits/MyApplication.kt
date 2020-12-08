package com.example.habits

import android.app.Application
import com.example.habits.common.dependencyinjection.AppCompositionRoot

class MyApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        super.onCreate()
        appCompositionRoot = AppCompositionRoot(this)
    }
}
