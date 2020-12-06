package com.example.habits.screens.questionslist

import android.os.Bundle
import com.example.habits.R
import com.example.habits.networking.StackoverflowApi
import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.screens.activities.BaseActivity
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class QuestionsListActivity : BaseActivity() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: QuestionListViewMvc
    private lateinit var stackOverflowApi: StackoverflowApi
    private lateinit var fetchQuestionListUseCase: FetchQuestionListUseCase
    private lateinit var dialogsNavigator: DialogsNavigator
    private lateinit var screensNavigator: ScreensNavigator

    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_frame)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, QuestionsListFragment())
                .commit()
        }
    }
}
