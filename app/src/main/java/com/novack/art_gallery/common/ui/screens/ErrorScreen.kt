package com.novack.art_gallery.common.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.novack.art_gallery.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import com.novack.art_gallery.ui.common.Padding

const val ERROR_SCREEN_TEST_TAG = "error_screen"

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, onBackClick: (() -> Unit)? = null) {
    Column(modifier = modifier.testTag(ERROR_SCREEN_TEST_TAG)) {
        if (onBackClick != null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.semantics(mergeDescendants = true) {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = stringResource(R.string.accessibility_back_button)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = Padding.SizeXS))
        }
        Box {
            Column(
                modifier = Modifier.matchParentSize(),
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
    }


}