package com.example.appcentnasachallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.model.Photos
import kotlinx.android.synthetic.main.recyclerview_photo_row.view.*

class RoversPhotoAdapter(val roverPhotos: ArrayList<APIRoverModel>) :
    RecyclerView.Adapter<RoversPhotoAdapter.CustomViewHolder>() {

    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_photo_row,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val photo = roverPhotos[position].photos[position].img_src
        Glide.with(holder.view.context)
            .load(photo)
            .into(holder.view.rover_photo)
    }

    override fun getItemCount(): Int = roverPhotos.size

    fun updateRoverPhotos(newPhotos: List<APIRoverModel>) {
        roverPhotos.clear()
        roverPhotos.addAll(newPhotos)
        notifyDataSetChanged()
    }


}