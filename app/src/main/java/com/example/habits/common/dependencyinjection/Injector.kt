package com.example.habits.common.dependencyinjection

import com.example.habits.questions.FetchQuestionListUseCase
import com.example.habits.questions.FetchQuestionUseCase
import com.example.habits.screens.common.dialogs.DialogsNavigator
import com.example.habits.screens.common.screens.ScreensNavigator
import com.example.habits.screens.common.viewsmvc.ViewMvcFactory
import java.lang.reflect.Field

class Injector(private val compositionRoot: PresentationCompositionRoot) {
    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            DialogsNavigator::class.java -> {
                return compositionRoot.dialogsNavigator
            }
            ScreensNavigator::class.java -> {
                return compositionRoot.screensNavigator
            }
            FetchQuestionListUseCase::class.java -> {
                return compositionRoot.fetchQuestionListUseCase
            }
            FetchQuestionUseCase::class.java -> {
                return compositionRoot.fetchQuestionUseCase
            }
            ViewMvcFactory::class.java -> {
                return compositionRoot.viewMvcFactory
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}
