package com.novack.art_gallery.common.data.mappers

import com.novack.art_gallery.common.data.model.OverviewDataDTO
import com.novack.art_gallery.common.data.model.OverviewResponseDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.Before

class ArtworksOverviewToDomainTest {

    private lateinit var overviewResponseDTO: OverviewResponseDTO
    private lateinit var overviewDataDTO: OverviewDataDTO

    @Before
    fun setup() {
        // Initialize DTOs with sample data for testing
        overviewDataDTO = OverviewDataDTO(
            id = 1,
            title = "Test Title",
            imageId = "test_image_id",
            artistName = "Test Artist"
        )
        overviewResponseDTO = OverviewResponseDTO(
            data = listOf(
                overviewDataDTO,
                OverviewDataDTO(
                    id = 2,
                    title = "Another Title",
                    imageId = "another_image",
                    artistName = "Another Artist"
                )
            )
        )
    }

    @Test
    fun `OverviewResponseDTO toDomain filters null imageId`() {
        val dtoWithNullImage = OverviewDataDTO(
            id = 3,
            title = "Null Image",
            imageId = null,
            artistName = "Null Image Artist"
        )
        val responseWithNullImage = OverviewResponseDTO(data = listOf(overviewDataDTO, dtoWithNullImage))

        val domainList = responseWithNullImage.toDomain()

        assertEquals(1, domainList.size)
        assertEquals(overviewDataDTO.id.toString(), domainList[0].id)
    }

    @Test
    fun `OverviewResponseDTO toDomain returns empty list for empty data`() {
        val emptyResponse = OverviewResponseDTO(data = emptyList())
        val domainList = emptyResponse.toDomain()
        assertTrue(domainList.isEmpty())
    }

    @Test
    fun `OverviewResponseDTO toDomain maps all valid items`() {
        val domainList = overviewResponseDTO.toDomain()

        assertEquals(2, domainList.size)

        // Verify the first item
        val firstArtwork = domainList[0]
        assertEquals("1", firstArtwork.id)
        assertEquals("Test Title", firstArtwork.title)
        assertEquals("Test Artist", firstArtwork.artist)
        assertEquals("https://www.artic.edu/iiif/2/test_image_id/full/843,/0/default.jpg", firstArtwork.thumbnailUrl)

        // Verify the second item
        val secondArtwork = domainList[1]
        assertEquals("2", secondArtwork.id)
        assertEquals("Another Title", secondArtwork.title)
        assertEquals("Another Artist", secondArtwork.artist)
        assertEquals("https://www.artic.edu/iiif/2/another_image/full/843,/0/default.jpg", secondArtwork.thumbnailUrl)
    }

    @Test
    fun `OverviewDataDTO toDomain maps all fields correctly`() {
        val artworkOverview = overviewDataDTO.toDomain()

        assertEquals("1", artworkOverview.id)
        assertEquals("Test Title", artworkOverview.title)
        assertEquals("Test Artist", artworkOverview.artist)
        assertEquals("https://www.artic.edu/iiif/2/test_image_id/full/843,/0/default.jpg", artworkOverview.thumbnailUrl)
    }

    @Test
    fun `OverviewDataDTO toDomain handles null artistName`() {
        val dtoWithNullArtist = overviewDataDTO.copy(artistName = null)
        val artworkOverview = dtoWithNullArtist.toDomain()

        assertEquals("1", artworkOverview.id)
        assertEquals("Test Title", artworkOverview.title)
        assertEquals(null, artworkOverview.artist) // Expect null
        assertEquals("https://www.artic.edu/iiif/2/test_image_id/full/843,/0/default.jpg", artworkOverview.thumbnailUrl)
    }

    @Test
    fun `OverviewDataDTO toDomain handles null title`() {
        val dtoWithNullArtist = overviewDataDTO.copy(title = null)
        val artworkOverview = dtoWithNullArtist.toDomain()

        assertEquals("1", artworkOverview.id)
        assertEquals("Test Artist", artworkOverview.artist)
        assertEquals(null, artworkOverview.title) // Expect null
        assertEquals("https://www.artic.edu/iiif/2/test_image_id/full/843,/0/default.jpg", artworkOverview.thumbnailUrl)
    }
}
