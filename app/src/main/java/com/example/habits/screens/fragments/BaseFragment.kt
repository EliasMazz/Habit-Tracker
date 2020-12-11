package com.example.habits.screens.fragments

import androidx.fragment.app.Fragment
import com.example.habits.common.dependencyinjection.DaggerPresentationComponent
import com.example.habits.common.dependencyinjection.Injector
import com.example.habits.common.dependencyinjection.PresentationModule
import com.example.habits.screens.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule((requireActivity() as BaseActivity).activityCompositionRoot))
            .build()
    }

    protected val injector get() = Injector(presentationComponent)
}
