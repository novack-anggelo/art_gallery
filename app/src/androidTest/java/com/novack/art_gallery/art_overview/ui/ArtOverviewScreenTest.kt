package com.novack.art_gallery.art_overview.ui

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.novack.art_gallery.art_overview.ui.components.OVERVIEW_ITEM_TEST_TAG
import com.novack.art_gallery.common.ui.screens.ERROR_SCREEN_TEST_TAG
import com.novack.art_gallery.common.ui.screens.LOADING_SCREEN_TEST_TAG
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test

class ArtOverviewScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun artOverviewScreen_withLoadingState_showsLoading() {
        // GIVEN
        val param = ArtOverviewParam.Loading

        // WHEN
        composeTestRule.setContent {
            MaterialTheme {
                ArtOverviewScreen(param = param, onPreviewClick = {})
            }
        }

        // THEN
        composeTestRule.onNodeWithTag(LOADING_SCREEN_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ERROR_SCREEN_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun artOverviewScreen_withErrorState_showsError() {
        // GIVEN
        val param = ArtOverviewParam.Error

        // WHEN
        composeTestRule.setContent {
            MaterialTheme {
                ArtOverviewScreen(param = param, onPreviewClick = {})
            }
        }

        // THEN
        composeTestRule.onNodeWithTag(ERROR_SCREEN_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(LOADING_SCREEN_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun artOverviewScreen_withLoadedState_showsContentAndHandlesClicks() {
        // GIVEN
        val artPieces = listOf(
            ArtOverviewItemParam(id = "1", title = "Title 1", thumbnailUrl = "imageId1", artist = "Artist 1"),
            ArtOverviewItemParam(id = "2", title = "Title 2", thumbnailUrl = "imageId2", artist = "Artist 2"),
        ).toImmutableList()
        val param = ArtOverviewParam.Loaded(artPieces)
        val onPreviewClick: (String) -> Unit = mockk() {
            every { this@mockk("1") } returns Unit
        }

        // WHEN
        composeTestRule.setContent {
            MaterialTheme {
                ArtOverviewScreen(param = param, onPreviewClick = onPreviewClick)
            }
        }

        // THEN
        composeTestRule.onNodeWithTag(LOADING_SCREEN_TEST_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(ERROR_SCREEN_TEST_TAG).assertDoesNotExist()

        // Verify that the items are displayed
        composeTestRule.onNodeWithTag(OVERVIEW_ITEM_TEST_TAG + "1").assertIsDisplayed()
        composeTestRule.onNodeWithTag(OVERVIEW_ITEM_TEST_TAG + "2").assertIsDisplayed()

        // Verify click handling
        composeTestRule.onNodeWithTag(OVERVIEW_ITEM_TEST_TAG + "1").performClick()
        verify(exactly = 1){ onPreviewClick("1") }
    }
}