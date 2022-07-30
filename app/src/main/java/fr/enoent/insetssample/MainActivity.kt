package fr.enoent.insetssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.workflow1.WorkflowExperimentalRuntime
import com.squareup.workflow1.config.AndroidRuntimeConfigTools
import com.squareup.workflow1.ui.ViewRegistry
import com.squareup.workflow1.ui.WorkflowLayout
import com.squareup.workflow1.ui.WorkflowUiExperimentalApi
import com.squareup.workflow1.ui.asScreen
import com.squareup.workflow1.ui.container.withEnvironment
import com.squareup.workflow1.ui.container.withRegistry
import com.squareup.workflow1.ui.renderWorkflowIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity() {
    @OptIn(WorkflowUiExperimentalApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val model: HelloComposeModel by viewModels()
        setContentView(
            WorkflowLayout(this).apply {
                take(
                    lifecycle,
                    model.renderings.map {
                        asScreen(it)
                            .withEnvironment()
                            .withRegistry(
                                ViewRegistry(
                                    InsetBinding(),
                                    NavigationBinding(),
                                ),
                            )
                    }
                )
            }
        )
    }

    class HelloComposeModel(savedState: SavedStateHandle) : ViewModel() {
        @OptIn(WorkflowUiExperimentalApi::class, WorkflowExperimentalRuntime::class)
        val renderings: StateFlow<Any> by lazy {
            renderWorkflowIn(
                workflow = NavigationWorkflow,
                scope = viewModelScope,
                savedStateHandle = savedState,
                runtimeConfig = AndroidRuntimeConfigTools.getAppWorkflowRuntimeConfig()
            )
        }
    }
}