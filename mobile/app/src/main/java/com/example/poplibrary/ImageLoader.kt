package com.example.poplibrary

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class ImageLoader: AsyncTask<String, Void, Bitmap>() {
    override fun doInBackground(vararg params: String?): Bitmap? {
        var image: Bitmap? = null
        try {
            Log.d("IMAGE", "Attempting to load image")
            val io: InputStream = URL(params[0]).openStream()
            image = BitmapFactory.decodeStream(io)
        } catch (e: Exception) {
            Log.d("IMAGE", "Failed to load image from " + params[0], e)
        }
        return image
    }
}