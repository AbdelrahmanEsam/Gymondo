package com.apptikar.gymondo.core.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.material3.CircularProgressIndicator

@Composable
fun LoadingPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Center
    ) {
        LoadingIndicator()
    }
}

@Composable
fun BoxScope.LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .pointerInput(Unit) {}
            .then(modifier)
    )
    CircularProgressIndicator(
        modifier = Modifier.align(Center),
        color = MaterialTheme.colorScheme.primary,
    )
}