package com.example.habits.screens.questiondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.habits.R
import com.example.habits.screens.common.viewsmvc.BaseViewMvc

class QuestionDetailsViewMvc(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
) : BaseViewMvc<QuestionDetailsViewMvc.Listener>(
    layoutInflater,
    viewGroup,
    R.layout.layout_question_details
) {
    private val swipeRefresh: SwipeRefreshLayout
    private val txtQuestionBody: TextView

    interface Listener {
        fun onRefreshClicked()
    }

    fun setQuestionBodyText(questionBody: CharSequence) {
        txtQuestionBody.text = questionBody
    }

    fun enableSwipeToRefresh() {
        swipeRefresh.isEnabled = true
    }

    fun disableSwipeToRefresh() {
        swipeRefresh.isEnabled = false
    }

    init {
        txtQuestionBody = findViewById(R.id.txt_question_body)
        swipeRefresh = findViewById(R.id.swipe_refresh)
        swipeRefresh.isEnabled = false

        swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }
}

