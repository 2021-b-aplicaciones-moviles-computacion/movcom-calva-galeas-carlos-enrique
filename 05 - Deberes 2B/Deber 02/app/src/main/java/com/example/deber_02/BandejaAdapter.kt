package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class BandejaAdapter(
    private val context: MainActivity,
    private val listaBandeja: List<Bandeja>,
    private val recyclerView: RecyclerView,
): RecyclerView.Adapter<BandejaAdapter.MyViewHolder>() {
    inner class  MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val list = mutableListOf<CarouselItem>()
        var usuario: TextView
        var descripcion: TextView
        var icPerfil: ImageView


        init {


            usuario = view.findViewById(R.id.tituloTextView)
            descripcion = view.findViewById(R.id.descritiotextView)
            icPerfil = view.findViewById(R.id.IcPerfil)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_bandeja_view, //Definimos la vista de nuestro recyvler
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vid = listaBandeja[position]
        holder.usuario.text = vid.usuario
        holder.descripcion.text = vid.descripcion
        holder.icPerfil.setImage(CarouselItem("https://i.pinimg.com/474x/d0/f2/4f/d0f24f66588ea096daaeff1d31dc4314.jpg"))

    }

    override fun getItemCount(): Int {
        return listaBandeja.size
    }

}