package com.novack.art_gallery.common.domain

interface Logger {
    fun e(tag: String, message: String?, cause: Throwable? = null)
}
