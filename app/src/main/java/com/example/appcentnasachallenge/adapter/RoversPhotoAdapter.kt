package com.example.appcentnasachallenge.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.model.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pop_up.view.*
import kotlinx.android.synthetic.main.recyclerview_photo_row.view.*
import kotlinx.android.synthetic.main.recyclerview_photo_row.view.rover_name
import kotlinx.android.synthetic.main.recyclerview_photo_row.view.rover_status

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



        holder.view.setOnClickListener {
            val mDialogView = LayoutInflater.from(it.context.applicationContext).inflate(R.layout.pop_up,null)

            Glide.with(it.context.applicationContext)
                .load(photo)
                .centerCrop()
                .dontAnimate()
                .into(mDialogView.roverImage)

            mDialogView.rover_date.text = "Rover Date = ${roverPhotos!!.photos[position].earth_date}"
            mDialogView.rover_name.text = "Rover Name = ${roverPhotos!!.photos[position].rover.name}"
            mDialogView.rover_cameraName.text = "Rover Camera = ${roverPhotos!!.photos[position].camera.full_name}"
            mDialogView.rover_status.text = "Rover Status = ${roverPhotos!!.photos[position].rover.status}"
            mDialogView.rover_launchDate.text = "Rover Launch Date = ${roverPhotos!!.photos[position].rover.launch_date}"
            mDialogView.rover_landingDate.text = "Rover Landing Date = ${roverPhotos!!.photos[position].rover.landing_date}"

            val mBuilder = AlertDialog.Builder(it.context)
                .setView(mDialogView)
            mBuilder.show()


        }



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