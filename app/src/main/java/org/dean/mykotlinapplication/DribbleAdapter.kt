package org.dean.mykotlinapplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
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

    //internal constructor 为主构造函数,修饰符为 private、 protected、 internal 和 public
    class PhotoHolder internal constructor(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var photo: Teaser? = null

        init {
            //主构造函数不能包含任何的代码，
            //初始化的代码可以放到 init 块中
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showPhotoIntent = Intent(context, PhotoActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, this.photo?.images?.teaser)
            context.startActivity(showPhotoIntent)
        }

        fun bindPhoto(photo: Teaser) {
            this.photo = photo
            GlideApp.with(view.context)
                    .load(photo.images.teaser)
                    .centerCrop()
                    .into(view.itemImage)
        }

        companion object {
            //静态变量
            private val PHOTO_KEY = "PHOTO"
        }
    }
}