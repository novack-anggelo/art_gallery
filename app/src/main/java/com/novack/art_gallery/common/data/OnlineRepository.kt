package com.novack.art_gallery.common.data

import com.novack.art_gallery.common.data.mappers.toDomain
import com.novack.art_gallery.common.data.retrofit.RetrofitNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnlineRepository @Inject constructor(
    private val api: RetrofitNetwork
) : ArtRepository {
    override suspend fun getArtworksOverview() = withContext(Dispatchers.IO) {
        api.getArtworksOverview().toDomain()
    }

}