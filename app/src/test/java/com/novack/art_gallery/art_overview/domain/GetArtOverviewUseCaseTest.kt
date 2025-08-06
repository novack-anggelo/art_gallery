package com.novack.art_gallery.art_overview.domain

import com.novack.art_gallery.art_overview.domain.models.ArtworkOverview
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

class GetArtOverviewUseCaseTest {

    private val mockRepository: ArtRepository = mockk()
    private val mockLogger: Logger = mockk() {
        every { e(any(), any(), any()) } answers { println("Mocked error: ${args[1]}") }
    }
    private val getArtOverviewUseCase = GetArtOverviewUseCase(mockRepository, mockLogger)

    @Test
    fun `invoke() should emit Loading then Loaded on successful data fetch`() = runTest {
        // Given
        val fakeArtworks = listOf(ArtworkOverview("1", "Artwork 1", "Artist 1", "image1"))
        coEvery { mockRepository.getArtworksOverview() } returns fakeArtworks

        // When
        val emissions = getArtOverviewUseCase().toList()

        // Then
        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is ArtworksOverviewState.Loading)
        assertTrue(emissions[1] is ArtworksOverviewState.Loaded)
        assertEquals(fakeArtworks, (emissions[1] as ArtworksOverviewState.Loaded).artworks)
    }

    @Test
    fun `invoke() should emit Loading then Error when repository throws exception`() = runTest {
        // Given
        val exception = IOException("Network Error")
        coEvery { mockRepository.getArtworksOverview() } throws exception

        // When
        val emissions = getArtOverviewUseCase().toList()

        // Then
        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is ArtworksOverviewState.Loading)
        assertTrue(emissions[1] is ArtworksOverviewState.Error)
    }
}
