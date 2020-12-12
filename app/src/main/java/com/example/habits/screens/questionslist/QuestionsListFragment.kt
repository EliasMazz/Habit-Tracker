package com.example.habits.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.model.Question
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import com.example.habits.screens.common.viewsmvc.ViewMvcFactory
import com.example.habits.screens.fragments.BaseFragment
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionsListFragment : BaseFragment(), QuestionListViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject lateinit var fetchQuestionListUseCase: FetchQuestionListUseCase
    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var viewMvc: QuestionListViewMvc
    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = viewMvcFactory.newQuestionsListViewMvc(container)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(clickedQuestion: Question) {
        screensNavigator.startQuestionDetails(clickedQuestion.id)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            val result = fetchQuestionListUseCase.fetchLatestQuestions()
            try {
                when (result) {
                    is FetchQuestionListUseCase.Result.Success -> {
                        viewMvc.bindQuestions(result.questions)
                        isDataLoaded = true
                    }
                    is FetchQuestionListUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }
}
