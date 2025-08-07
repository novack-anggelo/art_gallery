package com.novack.art_gallery.art_overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novack.art_gallery.art_overview.domain.ArtworksOverviewState
import com.novack.art_gallery.art_overview.domain.GetArtOverviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ArtworkOverviewViewModel @Inject constructor(
    useCase: GetArtOverviewUseCase
) : ViewModel() {

    val fetchState = useCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ArtworksOverviewState.Loading
        )
}