package org.dean.mykotlinapplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_dribble_row.view.*
import org.dean.mykotlinapplication.data.Teaser
import org.dean.mykotlinapplication.extensions.inflate

/**
 * Created by dean on 2017/11/28.
 */
class DribbleAdapter(private val photos: List<Teaser>) : RecyclerView.Adapter<DribbleAdapter.PhotoHolder>() {
    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: DribbleAdapter.PhotoHolder, position: Int) {
        val itemPhoto = photos[position]
        holder.bindPhoto(itemPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DribbleAdapter.PhotoHolder {
        val inflatedView = parent.inflate(R.layout.item_dribble_row)
        return PhotoHolder(inflatedView)
    }

    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var photo: Teaser? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showPhotoIntent = Intent(context, PhotoActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, photo?.images?.teaser)
            context.startActivity(showPhotoIntent)
        }

        fun bindPhoto(photo: Teaser) {
            this.photo = photo
            Picasso.with(view.context).load(photo.images.teaser).into(view.itemImage)
        }

        companion object {
            private val PHOTO_KEY = "PHOTO"
        }
    }
}