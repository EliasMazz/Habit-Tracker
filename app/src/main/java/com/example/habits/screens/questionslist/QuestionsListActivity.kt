package com.example.habits.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.MyApplication
import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.model.Question
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.*

class QuestionsListActivity : AppCompatActivity(), QuestionListViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: QuestionListViewMvc
    private lateinit var stackOverflowApi: StackoverflowApi
    private lateinit var fetchQuestionListUseCase: FetchQuestionListUseCase
    private lateinit var dialogsNavigator: DialogsNavigator

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = QuestionListViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView)

        fetchQuestionListUseCase =
            FetchQuestionListUseCase(((application as MyApplication).retrofit))

        dialogsNavigator = DialogsNavigator(supportFragmentManager)
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
        QuestionDetailsActivity.start(this, clickedQuestion.id)
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
