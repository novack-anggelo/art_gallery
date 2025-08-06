package com.novack.art_gallery.art_details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.novack.art_gallery.R
import com.novack.art_gallery.common.ui.screens.ErrorScreen
import com.novack.art_gallery.common.ui.screens.LoadingScreen
import com.novack.art_gallery.ui.common.ImageSize
import com.novack.art_gallery.ui.common.Padding

@Composable
fun ArtDetailsScreen(
    param: ArtDetailsParam,
    onBackClick: () -> Unit,
) {
    Scaffold { paddingValues ->
        when (param) {
            ArtDetailsParam.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize().padding(paddingValues))
            }

            ArtDetailsParam.Error -> {
                ErrorScreen(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    onBackClick = onBackClick,
                )
            }

            is ArtDetailsParam.Loaded -> {
                LoadedContent(
                    param = param,
                    paddingValues = paddingValues,
                    onBackClick = onBackClick,
                )
            }
        }
    }

}

@Composable
private fun LoadedContent(
    param: ArtDetailsParam.Loaded,
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(Padding.SizeXS),
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.semantics(mergeDescendants = true) {}
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = stringResource(R.string.accessibility_back_button)
            )
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(param.artDetails.imageUrl)
                .crossfade(true) // Smooth fade-in animation
                .build(),
            placeholder = painterResource(R.drawable.ic_art_placeholder),
            error = painterResource(R.drawable.ic_art_error),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Crop to fill the bounds
            modifier = Modifier
                .size(ImageSize.SizeM),
            onError = {
                println("error Loading image: ${it.result.throwable}")
            },
            onLoading = {
                println("Loading thumbnail for art: ${param.artDetails.id}")
            }
        )
        Text(
            text = stringResource(
                R.string.art_details_title,
                param.artDetails.title ?: stringResource(R.string.title_unknown)
            ),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = Padding.SizeXXXS),
        )
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = Padding.SizeXXXS), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(
                    R.string.art_details_description,
                    param.artDetails.description
                        ?: stringResource(R.string.art_details_no_description)
                ),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            Text(
                text = stringResource(
                    R.string.art_details_date,
                    param.artDetails.date ?: stringResource(R.string.art_details_date_unknown)
                ),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = stringResource(
                R.string.art_details_artist,
                param.artDetails.artist ?: stringResource(R.string.artist_unknown)
            ),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@PreviewLightDark
@Composable
private fun ArtDetailsScreenPreview(
    @PreviewParameter(ArtDetailsParamProvider::class) param: ArtDetailsParam
) {
    MaterialTheme {
        ArtDetailsScreen(
            param = param,
            onBackClick = {},
        )
    }
}