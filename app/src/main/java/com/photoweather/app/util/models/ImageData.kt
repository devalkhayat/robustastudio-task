package com.photoweather.app.util.models

import android.graphics.Bitmap

data class ImageData(
      var largeImageLocation:String?=null,
      var largeConvertedBitMap: Bitmap?=null,
      var smallImageLocation:String?=null,
      var smallConvertedBitMap: Bitmap?=null
)
