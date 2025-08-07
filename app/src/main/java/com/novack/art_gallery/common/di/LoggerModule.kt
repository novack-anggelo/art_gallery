package com.novack.art_gallery.common.di

import android.util.Log
import com.novack.art_gallery.common.domain.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

class AndroidLogger @Inject constructor() : Logger {
    override fun e(tag: String, message: String?, cause: Throwable?) {
        Log.e(tag, message, cause)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    @Singleton
    abstract fun bindLogger(impl: AndroidLogger): Logger
}
