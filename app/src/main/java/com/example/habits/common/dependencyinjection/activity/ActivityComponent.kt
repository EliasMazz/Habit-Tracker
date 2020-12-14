package com.example.habits.common.dependencyinjection.activity

import com.example.habits.common.dependencyinjection.app.AppComponent
import com.example.habits.common.dependencyinjection.presentation.PresentationComponent
import com.example.habits.common.dependencyinjection.presentation.PresentationModule
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}
