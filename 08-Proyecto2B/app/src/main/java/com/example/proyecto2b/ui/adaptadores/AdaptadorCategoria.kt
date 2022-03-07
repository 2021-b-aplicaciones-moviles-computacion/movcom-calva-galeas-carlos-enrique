package com.example.proyecto2b.ui.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.R
import com.example.proyecto2b.ui.clases.Categoria

class AdaptadorCategoria(private var lista: ArrayList<Categoria>, val contexto: Context) : RecyclerView.Adapter<AdaptadorCategoria.ViewHolder>() {


    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {


        val nombre: TextView



        init {

            nombre = vista.findViewById(R.id.icTvCategoria)




        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_categorias,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nombre = lista[position]


        holder.nombre.text = nombre.nombre



    }



}