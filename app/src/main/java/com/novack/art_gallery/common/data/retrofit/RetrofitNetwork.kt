package com.novack.art_gallery.common.data.retrofit

import com.novack.art_gallery.common.data.NetworkDataSource
import com.novack.art_gallery.common.data.model.OverviewResponseDTO
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private const val networkBaseUrl = "https://api.artic.edu/api/v1/"

private interface RetrofitNetworkApi {

    @GET(value = "artworks")
    suspend fun getArtworksOverview(
        @Query("limit") limit: String = "10",
    ): OverviewResponseDTO

}

@Singleton
class RetrofitNetwork @Inject constructor() : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(networkBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getArtworksOverview(): OverviewResponseDTO =
        networkApi.getArtworksOverview()

}

