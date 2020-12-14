package com.example.habits.common.dependencyinjection.app

import com.example.habits.common.dependencyinjection.activity.ActivityComponent
import com.example.habits.common.dependencyinjection.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}
