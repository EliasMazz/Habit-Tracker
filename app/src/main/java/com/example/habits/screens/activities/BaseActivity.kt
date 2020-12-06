package com.example.habits.screens.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.habits.MyApplication

open class BaseActivity : AppCompatActivity() {

    val compositionRoot get() = (application as MyApplication).appCompositionRoot
}
