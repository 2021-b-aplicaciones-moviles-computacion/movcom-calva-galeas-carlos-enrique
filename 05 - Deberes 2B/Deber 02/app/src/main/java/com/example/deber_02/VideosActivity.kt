package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class VideosActivity(
    private val context: MainActivity,
    private val videos: List<Videos>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<VideosActivity.MyViewHolder>(){
    inner class  MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var enlace = ""
        var video: ImageView
        var user: TextView
        var hash: TextView
        var music: TextView
        init {

            video = view.findViewById(R.id.videoTikTok)
            user = view.findViewById(R.id.txtUsuario)
            hash = view.findViewById(R.id.txtHashtag)
            music = view.findViewById(R.id.txtMusica)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.activity_videos, //Definimos la vista de nuestro recyvler
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vid = videos[position]
        holder.enlace = vid.enlace.toString()
        holder.video.setImage(CarouselItem(holder.enlace))
        holder.user.text = vid.usuario
        holder.hash.text = vid.hashtag
        holder.music.text = vid.musica

    }

    override fun getItemCount(): Int {
       return videos.size
    }
}