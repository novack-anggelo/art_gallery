package com.novack.art_gallery.common.data.di

import com.novack.art_gallery.common.data.ArtRepository
import com.novack.art_gallery.common.data.OnlineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindArtRepository(repository: OnlineRepository): ArtRepository
}