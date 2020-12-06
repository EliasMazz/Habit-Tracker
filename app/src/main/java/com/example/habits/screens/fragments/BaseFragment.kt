package com.example.habits.screens.fragments

import androidx.fragment.app.Fragment
import com.example.habits.screens.activities.BaseActivity

open class BaseFragment : Fragment() {

    protected val compositionRoot get() = (requireActivity() as BaseActivity).compositionRoot

}
