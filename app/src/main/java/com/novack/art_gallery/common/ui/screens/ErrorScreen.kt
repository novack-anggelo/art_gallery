package com.novack.art_gallery.common.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.novack.art_gallery.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

const val ERROR_SCREEN_TEST_TAG = "error_screen"

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.testTag(ERROR_SCREEN_TEST_TAG),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "😥",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(R.string.general_error_message),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}