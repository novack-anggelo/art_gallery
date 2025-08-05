package com.novack.art_gallery.art_overview.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.novack.art_gallery.R
import com.novack.art_gallery.ui.common.IconSize
import com.novack.art_gallery.ui.common.Padding
import com.novack.art_gallery.ui.theme.Art_galleryTheme

@Composable
fun OverviewItem(
    id: String,
    artist: String,
    title: String,
    thumbnailUrl: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick(id) },
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = Padding.SizeXS,
                vertical = Padding.SizeXXS,
            )
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(thumbnailUrl)
                        .crossfade(true) // Smooth fade-in animation
                        .build(),
                    placeholder = painterResource(R.drawable.ic_art_placeholder),
                    error = painterResource(R.drawable.ic_art_error),
                    contentDescription = "Thumbnail for art piece $title",
                    contentScale = ContentScale.Crop, // Crop to fill the bounds
                    modifier = Modifier
                        .size(IconSize.Thumbnail),
                    onError = {
                        println("error Loading image: ${it.result.throwable}")
                    },
                    onLoading = {
                        println("Loading thumbnail for art: $id")
                    }
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = Padding.SizeXXS),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = artist,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = Padding.SizeXXXXS)
                )
            }

        }
    }
}

@PreviewLightDark
@Composable
private fun OverviewItemPreview() {
    Art_galleryTheme {
        OverviewItem(
            id = "1",
            artist = "Artist",
            title = "Title",
            thumbnailUrl = "https://www.artic.edu/iiif/2/2d484387-2509-5e8e-2c43-22f9981972eb/full/843,/0/default.jpg",
            onClick = {}
        )
    }
}