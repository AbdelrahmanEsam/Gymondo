package com.apptikar.gymondo.core.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade


@Composable
fun AppImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String,
) {


    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build(),
        loading = {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        },
        colorFilter = colorFilter,
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}