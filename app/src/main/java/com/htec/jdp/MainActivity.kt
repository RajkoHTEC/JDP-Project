package com.htec.jdp

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContentResolverCompat.query
import com.htec.jdp.service.MyService
import java.io.File

private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    data class Images(val uri: Uri, val name: String, val size: Int)
    val imagesList = mutableListOf<Images>()
    private var currentImage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testAppSpecificStorage()
        testCacheFile()
        testExternal()
        val file = getAppSpecificAlbumStorageDir(this, "Pictures2")
        //Log.d(TAG, "onCreate: ${file.absolutePath}")

        val sharedPref = getSharedPreferences(MyService.PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(MyService.KEY_NAME, 1337)
            apply()
        }
        //commit() syncrhonously
        //apply() async

        val applicationSharedPref = applicationContext.getSharedPreferences(MyService.PREF_NAME, Context.MODE_PRIVATE)
        val data = applicationSharedPref.getInt(MyService.KEY_NAME, -1)
        Log.d(TAG, "onCreate: $data")

        startService(Intent(this, MyService::class.java))

        testSharedFiles()

        findViewById<Button>(R.id.btnLoadNextImage).setOnClickListener {
           if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
               if(imagesList.size > 0) {
                   val thumbnail: Bitmap = applicationContext.contentResolver.loadThumbnail(
                       imagesList[currentImage].uri,
                       Size(640, 480),
                       null

                   )
                   findViewById<ImageView>(R.id.ivShowImage).setImageBitmap(thumbnail)
                   currentImage = ((currentImage +1) % imagesList.size)

               }
           }
        }
    }

    private fun testSharedFiles() {
        applicationContext.contentResolver.query(
            //Audio Video Images Downloads Files
            MediaStore.Images.Media.INTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )?.use { cursor ->
            Log.d(TAG, "testSharedFiles: ${cursor.columnNames}")
            val names = cursor.columnNames
            while (cursor.moveToNext()) {
            }
        }


        val collection = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )

        val sortOrder = "${MediaStore.Images.Media.DISPLAY_NAME} ASC"

        val selection = null //"${MediaStore.Video.Media.DURATION} >= ?"
        val selectionArgs = arrayOf<String>() //arrayOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES).toString())

        val query = contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        query?.use {cursor ->
            val c = cursor.count

            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)

            while(cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val size = cursor.getInt(sizeColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )

                imagesList += Images(contentUri, name, size)
            }
        }
    }

    private fun testExternal() {
        Log.d(TAG, "testExternal: Is Writable: ${isExternalStorageWritable()}")
        Log.d(TAG, "testExternal: Is readable: ${isExternalStorageReadable()}")
        Log.d(TAG, "testExternal: ${getExternalFilesDir(null)?.absolutePath}")

        val filename = "my external file"
        val appSpecificExternalDir = File(getExternalFilesDir(null), filename)
        appSpecificExternalDir.createNewFile()
        appSpecificExternalDir.appendBytes("Success ".toByteArray())
    }

    private fun testCacheFile() {
        Log.d(TAG, "testCacheFile: ${cacheDir.absolutePath}")

        val filename = "myCacheFile"

        File.createTempFile(filename, null, cacheDir)
        val cacheFile = File(cacheDir, filename)
        //cacheFile.delete()
        //deleteFile(filename)
    }

    private fun isExternalStorageWritable() : Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isExternalStorageReadable() : Boolean {
        return Environment.getExternalStorageState() in setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    private fun testAppSpecificStorage() {
        val filename = "myfile"
        val fileContents = "Hello world!"


        // /data/user/0/com.htec.jdp/files
        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }

        openFileInput(filename).bufferedReader().useLines { lines ->
            val line = lines.fold("") {some, text ->
                "$some\n$text"
            }
            Log.d(TAG, "testAppSpecificStorage: $line")
        }

        Log.d(TAG, "testAppSpecificStorage: ${filesDir.absolutePath}")
    }

    private fun getAppSpecificAlbumStorageDir(context: Context, albumName: String): File {
        // Get the pictures directory that's inside the app-specific directory on
        // external storage.
        //storage/emulated/0/Android/data/com.htec.jdp/files/Pictures/Pictures2
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), albumName)
        if (!file.mkdirs()) {
            Log.e(TAG, "getAppSpecificAlbumStorageDir: Directory not created")
        } else {
            Log.d(TAG, "getAppSpecificAlbumStorageDir: Success")
        }
        return file
    }
}