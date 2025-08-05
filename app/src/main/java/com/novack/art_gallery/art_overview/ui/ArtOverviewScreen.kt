package com.novack.art_gallery.art_overview.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.novack.art_gallery.art_overview.ui.components.OverviewItem
import com.novack.art_gallery.ui.common.Padding
import com.novack.art_gallery.ui.theme.Art_galleryTheme

@Composable
fun ArtOverviewScreen(
    param: ArtOverviewParam,
    onPreviewClick: (String) -> Unit,
) {
    when(param) {
        is ArtOverviewParam.Loaded -> {
            LoadedContent(
                param = param,
                onPreviewClick = onPreviewClick
            )
        }
        ArtOverviewParam.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }

}

@Composable
private fun LoadedContent(param: ArtOverviewParam.Loaded, onPreviewClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = Padding.SizeXS)) {
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