package com.example.habits.common.dependencyinjection.activity

import com.example.habits.common.dependencyinjection.presentation.PresentationComponent
import com.example.habits.common.dependencyinjection.presentation.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}
