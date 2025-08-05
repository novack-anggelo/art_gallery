package com.novack.art_gallery.common.data

import com.novack.art_gallery.common.data.mappers.toDomain
import com.novack.art_gallery.common.data.retrofit.RetrofitNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnlineRepository constructor(
    private val api: RetrofitNetwork
) : ArtRepository {
    override suspend fun getArtworksOverview() = withContext(Dispatchers.IO) {
        api.getArtworksOverview().toDomain()
    }

}