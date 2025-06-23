package com.apptikar.gymondo.core.designSystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.apptikar.gymondo.core.designSystem.theme.buttonDisabledContainerColor
import com.apptikar.gymondo.core.designSystem.theme.buttonDisabledContentColor


@Composable
fun GymondoButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    shape: Shape = MaterialTheme.shapes.small,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    onClick: () -> Unit,
) {
    val animatedContainerColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.buttonDisabledContainerColor,
    )

    val animatedContentColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.buttonDisabledContentColor,
    )

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedContainerColor,
            contentColor = animatedContentColor,
            disabledContainerColor = MaterialTheme.colorScheme.buttonDisabledContainerColor,
            disabledContentColor = MaterialTheme.colorScheme.buttonDisabledContentColor
        )
    ) {
        Text(text = text, style = textStyle)
    }
}


@Preview
@Composable
private fun GymondoButtonEnabledPreview() {
    GymondoButton(onClick = { }, text = "Start Workout", isSelected = true)
}

@Preview
@Composable
private fun GymondoButtonSecondaryDisabledPreview() {
    GymondoButton(onClick = { }, text = "View Details")
}