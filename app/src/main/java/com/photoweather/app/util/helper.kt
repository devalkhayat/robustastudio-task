package com.photoweather.app.util

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.photoweather.resources.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


object helper {

    fun loadImage(context: Context, path:String?, photo: ImageView?){
        Glide.with(context).load(path)
            .placeholder(R.color.app_gray) // placeholder
            .error(R.color.app_gray) // image broken
            .fallback(R.color.app_gray) // no image
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(photo!!)
    }
    fun loadImageFromLocal(path:String):Bitmap{

        var photo = BitmapFactory.decodeFile(path)
        val matrix = Matrix()
        matrix.postRotate(90F)

        val rotated = Bitmap.createBitmap(
            photo!!, 0, 0, photo!!.width, photo!!.height,
            matrix, true
        )


        return rotated
    }


    fun viewToImage(view: View): Bitmap? {
        val returnedBitmap =
            Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
       // val bgDrawable: Drawable = view.getBackground()
        //if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    fun prepareImage(context: Context,bitmap: Bitmap, folder_name: String?,isThumb:Boolean): File? {
        var f: File
        val folder: File? = context.getExternalFilesDir(folder_name)
        return try {
            //create a file to write bitmap data
            f = File(folder, generateFileName())
            if (f.exists()) {
                f = File(folder, generateFileName())
            }
            f.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            if(isThumb) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50 /*ignored for JPG*/, bos)
            }else{
                bitmap.compress(Bitmap.CompressFormat.PNG, 100 /*ignored for PNG*/, bos)
            }
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(f)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            f
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun generateFileName(): String? {
        val generator = Random()
        var n = 10000
        n = generator.nextInt(n)
        val fname = "Image-$n.png"
        return fname
    }

    fun openShareDialog(context: Context,photoLocation:String){
        val b =BitmapFactory.decodeFile(photoLocation)
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        val bytes = ByteArrayOutputStream()
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context?.getContentResolver(), b, "Title", null)
        val imageUri: Uri = Uri.parse(path)
        //
        share.putExtra(Intent.EXTRA_STREAM, imageUri)
        context.startActivity(Intent.createChooser(share, "Select"))
    }

    fun checkCardDataIsValid(degree:CharSequence?,city:CharSequence?,location:CharSequence?):Boolean {
        return !(degree.isNullOrBlank() ||
                city.isNullOrBlank() ||
                location.isNullOrBlank())
    }


}