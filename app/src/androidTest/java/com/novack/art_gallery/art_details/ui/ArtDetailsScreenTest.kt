package com.novack.art_gallery.art_details.ui

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.novack.art_gallery.art_details.ui.ArtDetailsTestTags.ARTIST_TEST_TAG
import com.novack.art_gallery.art_details.ui.ArtDetailsTestTags.DATE_TEST_TAG
import com.novack.art_gallery.art_details.ui.ArtDetailsTestTags.DESCRIPTION_TEST_TAG
import com.novack.art_gallery.art_details.ui.ArtDetailsTestTags.LOADED_CONTENT_TEST_TAG
import com.novack.art_gallery.art_details.ui.ArtDetailsTestTags.TITLE_TEST_TAG
import com.novack.art_gallery.common.ui.screens.ERROR_SCREEN_TEST_TAG
import com.novack.art_gallery.common.ui.screens.LOADING_SCREEN_TEST_TAG
import org.junit.Rule
import org.junit.Test

class ArtDetailsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun artDetailsScreen_withLoadingState_showsLoading() {
        // GIVEN
        val param = ArtDetailsParam.Loading

        // WHEN
        composeTestRule.setContent {
            MaterialTheme {
                ArtDetailsScreen(param = param, onBackClick = {})
            }
        }

        // THEN
        composeTestRule.onNodeWithTag(LOADING_SCREEN_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ERROR_SCREEN_TEST_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(LOADED_CONTENT_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun artDetailsScreen_withErrorState_showsError() {
        // GIVEN
        val param = ArtDetailsParam.Error

        // WHEN
        composeTestRule.setContent {
            MaterialTheme {
                ArtDetailsScreen(param = param, onBackClick = {})
            }
        }

        // THEN
        composeTestRule.onNodeWithTag(ERROR_SCREEN_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(LOADING_SCREEN_TEST_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(LOADED_CONTENT_TEST_TAG).assertDoesNotExist()
    }

    @Test
    fun artDetailsScreen_withLoadedState_showsContent() {
        // GIVEN
        val artDetails = ArtDetailsInfoParam(
            id = "1",
            title = "The Starry Night",
            description = "A famous painting by Vincent van Gogh.",
            date = "1889",
            artist = "Vincent van Gogh",
            imageUrl = ""
        )
        val param = ArtDetailsParam.Loaded(artDetails)

        // WHEN
        composeTestRule.setContent {
            MaterialTheme {
                ArtDetailsScreen(param = param, onBackClick = {})
            }
        }

        // THEN
        composeTestRule.onNodeWithTag(LOADED_CONTENT_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(LOADING_SCREEN_TEST_TAG).assertDoesNotExist()
        composeTestRule.onNodeWithTag(ERROR_SCREEN_TEST_TAG).assertDoesNotExist()

        composeTestRule.onNodeWithTag(TITLE_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(DESCRIPTION_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(DATE_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ARTIST_TEST_TAG).assertIsDisplayed()
    }
}