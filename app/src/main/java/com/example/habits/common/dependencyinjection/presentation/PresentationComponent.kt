package com.example.habits.common.dependencyinjection.presentation

import com.example.habits.common.dependencyinjection.activity.ActivityComponent
import com.example.habits.screens.questiondetails.QuestionDetailsActivity
import com.example.habits.screens.questionslist.QuestionsListFragment
import dagger.Component

@PresentationScope
@Component(dependencies = [ActivityComponent::class], modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: QuestionsListFragment)
    fun inject(activity: QuestionDetailsActivity)
}
