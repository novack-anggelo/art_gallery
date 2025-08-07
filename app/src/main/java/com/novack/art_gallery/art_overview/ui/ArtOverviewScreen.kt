package com.novack.art_gallery.art_overview.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.novack.art_gallery.art_overview.ui.components.OverviewItem
import com.novack.art_gallery.common.ui.screens.ErrorScreen
import com.novack.art_gallery.common.ui.screens.LoadingScreen
import com.novack.art_gallery.ui.common.Padding
import com.novack.art_gallery.ui.theme.Art_galleryTheme

@Composable
fun ArtOverviewScreen(
    param: ArtOverviewParam,
    onPreviewClick: (String) -> Unit,
) {
    Scaffold { paddingValues ->
        when(param) {
            is ArtOverviewParam.Loaded -> {
                LoadedContent(
                    param = param,
                    paddingValues = paddingValues,
                    onPreviewClick = onPreviewClick
                )
            }
            ArtOverviewParam.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize().padding(paddingValues))
            }
            ArtOverviewParam.Error -> {
                ErrorScreen(modifier = Modifier.fillMaxSize().padding(paddingValues))
            }
        }
    }
}

@Composable
private fun LoadedContent(param: ArtOverviewParam.Loaded, paddingValues: PaddingValues, onPreviewClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(Padding.SizeXS)) {
        items(param.artPieces) { overview ->
            OverviewItem(
                param = overview,
                onClick = onPreviewClick,
                modifier = Modifier.padding(vertical = Padding.SizeXXS)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ArtOverviewScreenPreview(
    @PreviewParameter(ArtOverviewScreenParamProvider::class) param: ArtOverviewParam
) {
    Art_galleryTheme {
        ArtOverviewScreen(
        param = param,
        onPreviewClick = {},
        )
    }
}