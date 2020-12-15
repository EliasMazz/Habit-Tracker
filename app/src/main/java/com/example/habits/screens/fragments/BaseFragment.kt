package com.example.habits.screens.fragments

import androidx.fragment.app.Fragment
import com.example.habits.common.dependencyinjection.presentation.PresentationModule
import com.example.habits.screens.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent
}
