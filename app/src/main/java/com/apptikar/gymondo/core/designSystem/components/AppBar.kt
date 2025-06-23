package com.apptikar.gymondo.core.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun GymondoAppBar(
    modifier: Modifier = Modifier,
    thenModifier: Modifier = Modifier,
    title: String,
    leadingComposable: @Composable (BoxScope.() -> Unit)? = null,
    trailingComposable: @Composable (BoxScope.() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    elevation: Dp = 4.dp,
    titleColor: Color = MaterialTheme.colorScheme.onBackground
) {
    Box(
        modifier.fillMaxWidth().layout { measurable, constraints ->
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = constraints.maxWidth
                            + 40.dp.roundToPx()
                )
            )
            layout(placeable.width, placeable.height) {
                placeable.place(0, 0)
            }
        }.wrapContentHeight().shadow(
            elevation = elevation,
        ).background(backgroundColor).padding(12.dp).then(thenModifier),
    ) {
        leadingComposable?.invoke(this)
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 48.dp)
                .align(Alignment.Center),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
                .copy(color = titleColor),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        trailingComposable?.invoke(this)
    }

}