package com.example.habits.screens.fragments

import androidx.fragment.app.Fragment
import com.example.habits.common.dependencyinjection.presentation.DaggerPresentationComponent
import com.example.habits.common.dependencyinjection.presentation.PresentationModule
import com.example.habits.screens.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .activityComponent((requireActivity() as BaseActivity).activityComponent)
            .presentationModule(PresentationModule())
            .build()
    }

    protected val injector get() = presentationComponent
}
