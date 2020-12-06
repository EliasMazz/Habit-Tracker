package com.example.habits.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.FetchQuestionUseCase
import com.example.habits.screens.activities.BaseActivity
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import kotlinx.coroutines.*

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var stackOverflowApi: StackoverflowApi
    private lateinit var questionId: String
    private lateinit var questionDetailsViewMvc: QuestionDetailsViewMvc
    private lateinit var fetchQuestionUseCase: FetchQuestionUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionDetailsViewMvc = QuestionDetailsViewMvc(LayoutInflater.from(this), null)
        setContentView(questionDetailsViewMvc.rootView)

        dialogsNavigator = compositionRoot.dialogsNavigator
        screensNavigator = compositionRoot.screensNavigator

        fetchQuestionUseCase = compositionRoot.fetchQuestionUseCase

        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        questionDetailsViewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        questionDetailsViewMvc.unregisterListener(this)
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            questionDetailsViewMvc.disableSwipeToRefresh()
            questionDetailsViewMvc.showProgressIndication()
            val result = fetchQuestionUseCase.fetchQuestionDetails(questionId)
            try {
                when (result) {
                    is FetchQuestionUseCase.Result.Sucess -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            questionDetailsViewMvc.setQuestionBodyText(
                                Html.fromHtml(
                                    result.body,
                                    Html.FROM_HTML_MODE_LEGACY
                                )
                            )
                        } else {
                            questionDetailsViewMvc.setQuestionBodyText(Html.fromHtml(result.body))
                        }
                    }
                    is FetchQuestionUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                questionDetailsViewMvc.hideProgressIndication()
                questionDetailsViewMvc.enableSwipeToRefresh()
            }
        }
    }

    override fun onRefreshClicked() {
        fetchQuestionDetails()
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    override fun onSupportNavigateUp(): Boolean {
        screensNavigator.navigateBack()
        return true
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

}
