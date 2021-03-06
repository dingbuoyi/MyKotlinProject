package org.dean.mykotlinapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo.*

/**
 * Created by dean on 2017/11/28.
 */

class PhotoActivity : AppCompatActivity() {

    private var selectedPhotoUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_photo)

        selectedPhotoUrl = intent.getStringExtra(PHOTO_KEY)
        Picasso.with(this).load(selectedPhotoUrl).into(photoImageView)
    }

    companion object {
        private val PHOTO_KEY = "PHOTO"
    }
}