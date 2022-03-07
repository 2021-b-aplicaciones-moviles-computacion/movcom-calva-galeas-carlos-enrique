package com.example.proyecto2b.ui.adaptadores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.DetalleReceta
import com.example.proyecto2b.R
import com.example.proyecto2b.ui.clases.Receta

class AdaptadorMiPerfil(private var lista: ArrayList<Receta>, val contexto: Context) : RecyclerView.Adapter<AdaptadorMiPerfil.ViewHolder>() {

    // var listaOriginal: ArrayList<Pelicula>?=null

    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView
        val nombre: TextView
        var descripcion:TextView
        var autor: TextView
        var tiempoPreparacion: TextView


        init {
            imagen = vista.findViewById(R.id.bpimagen)
            calificacion = vista.findViewById(R.id.bptvcalificacion)
            nombre = vista.findViewById(R.id.bptvNombreReceta)
            descripcion = vista.findViewById(R.id.tvDescripcion)
            autor =  vista.findViewById(R.id.tvAutor)
            tiempoPreparacion = vista.findViewById(R.id.tv_TiempoPreparacion)
            //listaOriginal!!.addAll(lista)



        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_mi_perfil,parent,false)
        return ViewHolder(itemView)
    }



    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receta = lista[position]
        holder.imagen.setImageResource(receta.ImagePortada)
        holder.calificacion.text = receta.Calificacion
        holder.nombre.text = receta.Nombre
        holder.descripcion.text = receta.Descripcion
        holder.autor.text = receta.Autor
        holder.tiempoPreparacion.text = receta.tiempoPrearacion

        //////Imagen
        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetalleReceta::class.java).putExtra("receta",receta) )

        }


    }



}