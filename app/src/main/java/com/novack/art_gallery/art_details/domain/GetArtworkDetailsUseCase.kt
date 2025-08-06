package com.novack.art_gallery.art_details.domain

import com.novack.art_gallery.common.data.ArtRepository
import com.novack.art_gallery.common.domain.Logger
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArtworkDetailsUseCase @Inject constructor(
    private val repository: ArtRepository,
    private val logger: Logger,
) {
    operator fun invoke(id: String) = flow {
        emit(ArtworkDetailsState.Loading)
        try {
            val artworkDetails = repository.getArtworkDetails(id)
            emit(ArtworkDetailsState.Loaded(artworkDetails = artworkDetails))
        } catch (e: Exception) {
            logger.e("GetArtworkDetailsUseCase", "Error fetching artwork details", e)
            emit(ArtworkDetailsState.Error)

        }
    }
        .catch {
            logger.e("GetArtworkDetailsUseCase", "Error processing artwork details", it)
            emit(ArtworkDetailsState.Error)
        }
}