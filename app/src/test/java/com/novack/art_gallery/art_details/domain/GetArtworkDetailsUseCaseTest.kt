package com.novack.art_gallery.art_details.domain

import com.novack.art_gallery.art_details.domain.models.ArtworkDetails
import com.novack.art_gallery.common.data.ArtRepository
import com.novack.art_gallery.common.domain.Logger
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class GetArtworkDetailsUseCaseTest {

    private val mockRepository: ArtRepository = mockk()
    private val mockLogger: Logger = mockk() {
        every { e(any(), any(), any()) } answers { println("Mocked error: ${args[1]}") }
    }
    private val getArtworkDetailsUseCase = GetArtworkDetailsUseCase(mockRepository, mockLogger)

    @Test
    fun `invoke() should emit Loading then Loaded on successful data fetch`() = runTest {
        // Given
        val fakeArtwork = ArtworkDetails(
            id = "1",
            title = "Artwork 1",
            artist = "Artist 1",
            imageUrl = "image1",
            date = "1500",
            description = "Description 1"
        )
        coEvery { mockRepository.getArtworkDetails(any()) } returns fakeArtwork

        // When
        val emissions = getArtworkDetailsUseCase("1").toList()

        // Then
        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is ArtworkDetailsState.Loading)
        assertTrue(emissions[1] is ArtworkDetailsState.Loaded)
        assertEquals(fakeArtwork, (emissions[1] as ArtworkDetailsState.Loaded).artworkDetails)
    }

    @Test
    fun `invoke() should emit Loading then Error when repository throws exception`() = runTest {
        // Given
        val exception = IOException("Network Error")
        coEvery { mockRepository.getArtworkDetails(any()) } throws exception

        // When
        val emissions = getArtworkDetailsUseCase("1").toList()

        // Then
        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is ArtworkDetailsState.Loading)
        assertTrue(emissions[1] is ArtworkDetailsState.Error)
    }

}