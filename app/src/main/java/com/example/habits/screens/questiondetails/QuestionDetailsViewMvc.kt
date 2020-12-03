package com.example.habits.screens.questiondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.habits.R

class QuestionDetailsViewMvc(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
) {
    private val swipeRefresh: SwipeRefreshLayout
    private val txtQuestionBody: TextView

    val rootView = layoutInflater.inflate(R.layout.layout_question_details, viewGroup, false)

    private val listeners = HashSet<Listener>()

    interface Listener {
        fun onRefreshClicked()
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun setQuestionBodyText(questionBody: CharSequence) {
        txtQuestionBody.text = questionBody
    }

    fun enableSwipeToRefresh() {
        swipeRefresh.isEnabled = true
    }

    fun disableSwipeToRefresh(){
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

    fun <T : View?> findViewById(@IdRes id: Int): T = rootView.findViewById<T>(id)
}
