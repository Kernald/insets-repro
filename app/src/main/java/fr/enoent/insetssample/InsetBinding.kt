package fr.enoent.insetssample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.WorkflowUiExperimentalApi
import com.squareup.workflow1.ui.compose.ComposeScreenViewFactory

@OptIn(WorkflowUiExperimentalApi::class)
class InsetBinding : ComposeScreenViewFactory<InsetScreen>() {
    override val type = InsetScreen::class

    @Composable
    override fun Content(rendering: InsetScreen, viewEnvironment: ViewEnvironment) {
        MaterialTheme {
            Box(modifier = Modifier.systemBarsPadding()) {
                Button(
                    onClick = {},
                    modifier = Modifier.requiredHeight(64.dp)
                ) {
                    Text(text = rendering.text)
                }
            }
        }
    }
}