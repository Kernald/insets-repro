package fr.enoent.insetssample

import androidx.compose.ui.graphics.vector.ImageVector
import com.squareup.workflow1.ui.Screen
import com.squareup.workflow1.ui.WorkflowUiExperimentalApi

@OptIn(WorkflowUiExperimentalApi::class)
data class NavigationScreen(
    val currentScreen: Screen,
    val targets: Map<String, ImageVector>,
    val currentIndex: Int,
    val onTargetPressed: (Int) -> Unit,
) : Screen