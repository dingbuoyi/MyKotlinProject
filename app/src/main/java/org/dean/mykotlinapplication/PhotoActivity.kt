package org.dean.mykotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_photo.*

/**
 * Created by dean on 2017/11/28.
 */

class PhotoActivity : AppCompatActivity() {
    // 变量可以为空
    private var selectedPhotoUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_photo)

        selectedPhotoUrl = intent.getStringExtra(PhotoActivity.PHOTO_KEY)
        GlideApp.with(this)
                .load(selectedPhotoUrl)
                .centerCrop()
                .into(photoImageView)
    }

    companion object {
        // 相当于java里面的静态变量，并且一定要定义在class里面, 调用时候class.xx
        private val PHOTO_KEY = "PHOTO"
    }
}