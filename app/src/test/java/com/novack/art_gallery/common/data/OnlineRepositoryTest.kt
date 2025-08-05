package com.novack.art_gallery.common.data

import com.novack.art_gallery.common.data.mappers.toDomain
import com.novack.art_gallery.common.data.model.OverviewDataDTO
import com.novack.art_gallery.common.data.model.OverviewResponseDTO
import com.novack.art_gallery.common.data.retrofit.RetrofitNetwork
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class OnlineRepositoryTest {

    private lateinit var retrofitNetwork: RetrofitNetwork
    private lateinit var repository: OnlineRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        retrofitNetwork = mockk()
        repository = OnlineRepository(retrofitNetwork, testDispatcher)
    }

    @Test
    fun `getArtworksOverview successful response`() = runTest(testDispatcher) {
        // Arrange
        val fakeArtworksDTO = OverviewResponseDTO(
            data = listOf(
                OverviewDataDTO(
                    id = 1,
                    title = "Mona Lisa",
                    artistName = "Leonardo da Vinci",
                    imageId = "abc"
                ),
                OverviewDataDTO(
                    id = 2,
                    title = "The Starry Night",
                    artistName = "Vincent van Gogh",
                    imageId = "def"
                )
            )
        )
        val expectedArtworks = fakeArtworksDTO.data.map { it.toDomain() }
        coEvery { retrofitNetwork.getArtworksOverview() } returns fakeArtworksDTO

        // Act
        val result = repository.getArtworksOverview()

        // Assert
        assertEquals(expectedArtworks, result)
    }

    @Test
    fun `getArtworksOverview empty list response`() = runTest(testDispatcher) {
        // Arrange
        val fakeEmptyDTO = OverviewResponseDTO(data = emptyList())
        coEvery { retrofitNetwork.getArtworksOverview() } returns fakeEmptyDTO

        // Act
        val result = repository.getArtworksOverview()

        // Assert
        assert(result.isEmpty())
    }

    @Test(expected = IOException::class)
    fun `getArtworksOverview API error handling`() = runTest(testDispatcher) {
        // Arrange
        coEvery { retrofitNetwork.getArtworksOverview() } throws IOException("Network error")

        // Act
        repository.getArtworksOverview()
    }
}
