package com.example.habits

import android.app.Application
import com.example.habits.common.dependencyinjection.app.AppComponent
import com.example.habits.common.dependencyinjection.app.AppModule
import com.example.habits.common.dependencyinjection.app.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(
                AppModule(
                    this
                )
            )
            .build()
    }
}
