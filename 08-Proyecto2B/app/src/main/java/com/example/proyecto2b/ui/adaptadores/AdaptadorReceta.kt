package com.example.proyecto2b.ui.adaptadores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto2b.DetalleReceta
import com.example.proyecto2b.R
import com.example.proyecto2b.ui.clases.RecetaFirebase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdaptadorReceta(private var lista: ArrayList<RecetaFirebase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorReceta.ViewHolder>() {


    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView



        init {
            imagen = vista.findViewById(R.id.elpIvReceta)
            calificacion = vista.findViewById(R.id.tv_calificacion)




        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_receta,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receta = lista[position]

        val storage = Firebase.storage
        var imagenRef = storage.getReferenceFromUrl(receta.imagenReceta.toString())


        Glide.with(contexto)
            .load(imagenRef)
            .into(holder.imagen)


        //holder.imagen.setImageResource(pelicula.ImagePortada)
        holder.calificacion.text = receta.calificacion.toString()

        //////Imagen
        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetalleReceta::class.java).putExtra("receta",receta) )

        }



    }



}