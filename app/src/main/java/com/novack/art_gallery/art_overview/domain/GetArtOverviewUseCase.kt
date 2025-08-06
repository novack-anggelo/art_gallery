package com.novack.art_gallery.art_overview.domain

import android.util.Log
import com.novack.art_gallery.common.data.ArtRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArtOverviewUseCase @Inject constructor(
    private val repository: ArtRepository
) {
    operator fun invoke() = flow {
            emit(ArtworksOverviewState.Loading)
            try {
                val artworksOverview = repository.getArtworksOverview()
                emit(ArtworksOverviewState.Loaded(artworks = artworksOverview))
            } catch (e: Exception) {
                Log.e("Error fetching art overview", e.message, e.cause)
                emit(ArtworksOverviewState.Error)
            }
        }
        .catch {
            Log.e("Error processing art overview", it.message, it.cause)
            emit(ArtworksOverviewState.Error)
        }
}