package fr.enoent.insetssample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.WorkflowUiExperimentalApi
import com.squareup.workflow1.ui.compose.ComposeScreenViewFactory
import com.squareup.workflow1.ui.compose.WorkflowRendering

@OptIn(WorkflowUiExperimentalApi::class)
class NavigationBinding : ComposeScreenViewFactory<NavigationScreen>() {
    override val type = NavigationScreen::class

    @Composable
    override fun Content(rendering: NavigationScreen, viewEnvironment: ViewEnvironment) {
        Scaffold(
            bottomBar = {
                Box(modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())) {
                    BottomNavigation {
                        rendering.targets.onEachIndexed { index, (label, icon) ->
                            BottomNavigationItem(
                                selected = index == rendering.currentIndex,
                                onClick = { rendering.onTargetPressed(index) },
                                icon = { Icon(icon, contentDescription = null) },
                                label = { Text(label) },
                            )
                        }
                    }
                }
            }
        ) { contentPadding ->
            Box(
                Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            ) {
                WorkflowRendering(
                    rendering = rendering.currentScreen,
                    viewEnvironment = viewEnvironment,
                )
            }
        }
    }
}