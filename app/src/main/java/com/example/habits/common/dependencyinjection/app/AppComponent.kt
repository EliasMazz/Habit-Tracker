package com.example.habits.common.dependencyinjection.app

import android.app.Application
import com.example.habits.networking.StackoverflowApi
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getRetrofit(): Retrofit
    fun getStackoverflowApi(): StackoverflowApi
    fun application(): Application
}
