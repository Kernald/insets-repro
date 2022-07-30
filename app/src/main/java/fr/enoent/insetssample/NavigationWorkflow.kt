package fr.enoent.insetssample

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import com.squareup.workflow1.Snapshot
import com.squareup.workflow1.StatefulWorkflow
import com.squareup.workflow1.WorkflowAction.Companion.noAction
import com.squareup.workflow1.action
import com.squareup.workflow1.ui.WorkflowUiExperimentalApi

@OptIn(WorkflowUiExperimentalApi::class)
object NavigationWorkflow :
    StatefulWorkflow<Unit, NavigationWorkflow.State, Nothing, NavigationScreen>() {

    private val targets = mapOf(
        "Without backstack" to Icons.Default.Person,
        "With backstack" to Icons.Default.ShoppingCart,
    )

    data class State(val currentIndex: Int)

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return State(0)
    }

    override fun render(
        renderProps: Unit,
        renderState: State,
        context: RenderContext,
    ): NavigationScreen {
        return NavigationScreen(
            currentScreen = when(renderState.currentIndex) {
                0 -> context.renderChild(
                    BackStackWorkflow,
                    BackStackWorkflow.Props(hasBackStack = false),
                ) { noAction() }
                1 -> context.renderChild(
                    BackStackWorkflow,
                    BackStackWorkflow.Props(hasBackStack = true),
                ) { noAction() }
                else -> throw IndexOutOfBoundsException()
            },
            targets = targets,
            currentIndex = renderState.currentIndex,
            onTargetPressed = {
                context.actionSink.send(action { state = state.copy(currentIndex = it) })
            }
        )
    }

    override fun snapshotState(state: State): Snapshot? {
        return null
    }
}