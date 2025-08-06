package com.novack.art_gallery.art_details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novack.art_gallery.art_details.domain.ArtworkDetailsState
import com.novack.art_gallery.art_details.domain.GetArtworkDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ArtworkDetailsViewModel @Inject constructor(
    useCase: GetArtworkDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val artworkId: String = savedStateHandle.get<String>("artworkId")
        ?: throw IllegalStateException("artworkId not found in SavedStateHandle")

    val fetchState = useCase(artworkId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ArtworkDetailsState.Loading
        )

}