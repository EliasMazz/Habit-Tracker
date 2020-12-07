package com.example.habits.screens.fragments

import androidx.fragment.app.Fragment
import com.example.habits.common.composition.PresentationCompositionRoot
import com.example.habits.screens.activities.BaseActivity

open class BaseFragment : Fragment() {

    protected val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }
}
