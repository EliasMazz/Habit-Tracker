package com.example.habits.screens.fragments

import androidx.fragment.app.Fragment
import com.example.habits.common.dependencyinjection.Injector
import com.example.habits.common.dependencyinjection.PresentationCompositionRoot
import com.example.habits.screens.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}
