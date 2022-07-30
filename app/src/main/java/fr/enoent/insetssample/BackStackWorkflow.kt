package fr.enoent.insetssample

import com.squareup.workflow1.StatelessWorkflow
import com.squareup.workflow1.WorkflowAction.Companion.noAction
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.WorkflowUiExperimentalApi
import com.squareup.workflow1.ui.container.toBackStackScreen

@OptIn(WorkflowUiExperimentalApi::class)
object BackStackWorkflow : StatelessWorkflow<BackStackWorkflow.Props, Nothing, Screen>() {
    data class Props(
        val hasBackStack: Boolean,
    )

    override fun render(renderProps: Props, context: RenderContext): Screen {
        val child = context.renderChild(InsetWorkflow, Unit) { noAction() }

        return if (renderProps.hasBackStack) {
            listOf(child).toBackStackScreen()
        } else {
            child
        }
    }
}