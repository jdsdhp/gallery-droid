package com.jesusd0897.gallerydroid.util

import android.Manifest.permission.SET_WALLPAPER
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.annotation.IntRange
import androidx.annotation.RequiresPermission
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

const val EXTRA_USE_LABEL_TAG = "use_label_extra"

enum class ImageExtension(val label: String) {
    JPEG("jpeg"), PNG("png")
}

@RequiresPermission(SET_WALLPAPER)
fun setAsWallpaper(context: Context, fileName: String) {
    val wpm = WallpaperManager.getInstance(context)
    val ins = context.assets.open(fileName)
    wpm.setStream(ins)
}

@Throws(IOException::class)
fun bitmapToFile(
    context: Context,
    bitmap: Bitmap,
    extension: ImageExtension = ImageExtension.JPEG,
    imageFileName: String = "share_image_temp",
    @IntRange(from = 1, to = 100) quality: Int = 100,
): File {
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val filePic = File(storageDir, "$imageFileName.${extension.label}")
    filePic.createNewFile()

    val out = FileOutputStream(filePic)
    val format = when (extension) {
        ImageExtension.JPEG -> Bitmap.CompressFormat.JPEG
        ImageExtension.PNG -> Bitmap.CompressFormat.PNG
    }
    bitmap.compress(format, quality, out)
    out.close()
    return filePic
}

fun sharePicture(context: Context, uri: Uri) = context.startActivity(
    Intent(Intent.ACTION_SEND).setType("image/*")
        .putExtra(Intent.EXTRA_STREAM, uri)
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
)