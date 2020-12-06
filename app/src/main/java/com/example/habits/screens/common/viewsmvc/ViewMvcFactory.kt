package com.example.habits.screens.common.viewsmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.habits.screens.questiondetails.QuestionDetailsViewMvc
import com.example.habits.screens.questionslist.QuestionListViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun newQuestionsListViewMvc(parentViewGroup: ViewGroup?) =
        QuestionListViewMvc(layoutInflater, parentViewGroup)

    fun newQuestionsDetailViewMvc(parentViewGroup: ViewGroup?) =
        QuestionDetailsViewMvc(layoutInflater, parentViewGroup)
}
