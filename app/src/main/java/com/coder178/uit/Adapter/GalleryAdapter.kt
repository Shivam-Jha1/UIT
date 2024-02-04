package com.coder178.uit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Data.Gallery
import com.coder178.uit.R
import com.squareup.picasso.Picasso

class GalleryAdapter(val photos:ArrayList<Gallery>):RecyclerView.Adapter<GalleryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gallery_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val photo = photos[position]
        Picasso.get()
            .load(photo.galleryImage)
            .into(holder.galleryImage)

    }

    override fun getItemCount(): Int {
        return photos.size
    }


}

class GalleryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val galleryImage:ImageView = itemView.findViewById(R.id.galleryImage)

}
