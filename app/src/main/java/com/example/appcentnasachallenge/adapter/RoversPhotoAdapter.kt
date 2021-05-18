package com.example.appcentnasachallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.model.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_photo_row.view.*

class RoversPhotoAdapter(var roverPhotos: APIRoverModel?) :
    RecyclerView.Adapter<RoversPhotoAdapter.CustomViewHolder>() {

    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_photo_row,parent,false)
        return CustomViewHolder(cellForRow)
    }



    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        if(roverPhotos == null) return
        val photo = roverPhotos!!.photos[position].img_src

        Glide.with(holder.itemView.context.applicationContext)
            .load(photo)
            .centerCrop()
            .dontAnimate()
            .into(holder.view.rover_photo)
        //Picasso.get().load(photo).into(holder.itemView.rover_photo)



        //holder.view.rover_photo.setImageResource(R.mipmap.ic_launcher)

        val name_text = roverPhotos!!.photos[position].camera.full_name
        holder.view.rover_name.text = name_text

        val status_text = roverPhotos!!.photos[position].camera.name
        holder.view.rover_status.text = status_text
    }

    override fun getItemCount(): Int {
        if(roverPhotos == null) return 0
        return roverPhotos!!.photos.size
    }

    fun updateRoverPhotos(newPhotos: APIRoverModel) {

        roverPhotos ?: null

        if(roverPhotos != null) {

            roverPhotos!!.photos = newPhotos.photos
            notifyDataSetChanged()
        }



    }




}