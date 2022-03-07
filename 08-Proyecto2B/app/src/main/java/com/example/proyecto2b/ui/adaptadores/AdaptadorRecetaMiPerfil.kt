package com.example.proyecto2b.ui.adaptadores

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto2b.DetalleReceta
import com.example.proyecto2b.Navigation
import com.example.proyecto2b.R
import com.example.proyecto2b.ui.clases.RecetaFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdaptadorRecetaMiPerfil(private var lista: ArrayList<RecetaFirebase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorRecetaMiPerfil.ViewHolder>() {

    // var listaOriginal: ArrayList<Pelicula>?=null
    var flagBotton: Boolean = false

    inner class ViewHolder(var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView
        val nombre: TextView
        var descripcion: TextView
        var autor: TextView
        var duracion: TextView
        var boton: Button


        init {
            imagen = vista.findViewById(R.id.bpimagen)
            calificacion = vista.findViewById(R.id.btcalificacion)
            nombre = vista.findViewById(R.id.bptvNombreReceta)
            descripcion = vista.findViewById(R.id.tvDescripcion)
            autor = vista.findViewById(R.id.tvAutor)
            duracion = vista.findViewById(R.id.tv_TiempoPreparacion)
            boton = vista.findViewById(R.id.tbpBtanadir)
            //listaOriginal!!.addAll(lista)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mi_perfil_lista, parent, false)
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




        holder.calificacion.text = receta.calificacion.toString()
        holder.nombre.text = receta.nombre
        holder.descripcion.text = receta.descripcion
        holder.autor.text = receta.autor
        holder.duracion.text = receta.tiempoPreparacion.toString()

        //////Imagen
        holder.imagen.setOnClickListener {
            contexto.startActivity(
                Intent(contexto, DetalleReceta::class.java).putExtra(
                    "pel",
                    receta
                )
            )

        }

        holder.boton.setOnClickListener {
            val instanciaAuth = FirebaseAuth.getInstance()
            val usuarioLocal = instanciaAuth.currentUser
            val db = Firebase.firestore
            Log.i("ayuda","${receta.uidReceta}")
            val referencia = db
                .collection("usuario").document(usuarioLocal!!.email.toString())



            val RecetaGuardar = hashMapOf<String,Any>(
                "recetas" to FieldValue.delete()
            )
            referencia.update(RecetaGuardar).addOnCompleteListener { }
            contexto.startActivity(Intent(contexto, Navigation::class.java))





        }
    }



}
