package com.br.matchmovies.imagecapture

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FileHelper{
    companion object{
        fun createFileInStorage(context: Context): File?{
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            return File(getAppsFileDir(context), "img_$timestamp.png")
        }

        private fun getAppsFileDir(context: Context): File? {
            val file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            if (file != null && !file.exists()){
                file?.mkdirs()
            }
            return file
        }
    }
}