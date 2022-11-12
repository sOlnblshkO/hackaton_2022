package com.example.data.core.shared

import android.graphics.Bitmap
import android.net.Uri
import com.squareup.picasso.Picasso
import javax.inject.Inject

class FileLoader @Inject constructor() {

    fun getBitmap(url: String): Bitmap? {
        return Picasso.get().load(Uri.parse(url)).get()
    }

}