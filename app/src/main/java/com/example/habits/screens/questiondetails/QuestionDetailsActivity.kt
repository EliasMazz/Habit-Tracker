package com.example.habits.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.habits.Constants
import com.example.habits.R
import com.example.habits.networking.StackoverflowApi
import com.example.habits.screens.common.dialogs.ServerErrorDialogFragment
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionDetailsActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var txtQuestionBody: TextView
    private lateinit var stackOverflowApi: StackoverflowApi
    private lateinit var questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_question_details)

        txtQuestionBody = findViewById(R.id.txt_question_body)

        swipeRefresh = findViewById(R.id.swipe_refresh)
        swipeRefresh.isEnabled = false

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        stackOverflowApi = retrofit.create(StackoverflowApi::class.java)

        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            showProgressIndication()
            try {
                val response = stackOverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    hideProgressIndication()
                    val questionBody = response.body()!!.question.body
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        txtQuestionBody.text =
                            Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        txtQuestionBody.text = Html.fromHtml(questionBody)
                    }
                } else {
                    onFetchFailed()
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    onFetchFailed()
                }
            }
        }
    }

    private fun onFetchFailed() {
        supportFragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }

    private fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    private fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
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
