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
import com.example.proyecto2b.R
import com.example.proyecto2b.ui.clases.RecetaFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import android.annotation.SuppressLint


class AdaptadorRecetaBusqueda(private var lista: ArrayList<RecetaFirebase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorRecetaBusqueda.ViewHolder>() {

   companion object{
       var flagBotton:Boolean = false
   }

    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView
        val nombre: TextView
        var descripcion:TextView
        var autor: TextView
        var tiempoPreparacion: TextView
        var boton: Button


        init {
            imagen = vista.findViewById(R.id.bpimagen)
            calificacion = vista.findViewById(R.id.bptvcalificacion)
            nombre = vista.findViewById(R.id.bptvNombreReceta)
            descripcion = vista.findViewById(R.id.tvDescripcion)
            autor =  vista.findViewById(R.id.tvAutor)
            tiempoPreparacion = vista.findViewById(R.id.tv_TiempoPreparacion)
            boton = vista.findViewById(R.id.tbpBtanadir)
            //listaOriginal!!.addAll(lista)



        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itembusquedapelicula,parent,false)
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
        holder.tiempoPreparacion.text = receta.tiempoPreparacion

        //////Imagen
        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetalleReceta::class.java).putExtra("receta",receta) )

        }


        holder.boton.setOnClickListener {
         flagBotton = false
            botonCambiar(holder)


            val atributos = hashMapOf<String,String>(
                "descripcion" to receta.descripcion.toString(),
                "calificacion" to receta.calificacion.toString(),
                "autor" to receta.autor.toString(),
                "tiempoPreparacion" to receta.tiempoPreparacion.toString(),
                "imagen" to receta.imagenReceta.toString(),
                "nombre" to receta.nombre.toString(),
                "uid_Receta" to receta.uidReceta.toString()
            )


            val peliculaAGuardar = hashMapOf<String,HashMap<String,String>>(
                receta.uidReceta.toString() to atributos
            )


            val instanciaAuth = FirebaseAuth.getInstance()
            val usuarioLocal = instanciaAuth.currentUser

            val db = Firebase.firestore

            Log.i("transaccion", "User : ${usuarioLocal!!.email}")
            val referencia = db
                .collection("usuario").document(usuarioLocal!!.email.toString())

            referencia
                .get()
                .addOnSuccessListener {
                    if(it["recetas"] !=null) {

            db
                .runTransaction { transaction ->
                    val documentoActual = transaction.get(referencia)
                    val hashRecetas = documentoActual.get("recetas") as HashMap<String,Any>
                    Log.i("transaccion", "hash: ${hashRecetas}")

                    if(hashRecetas != {}){
                        hashRecetas.put(receta.uidReceta.toString(), atributos)
                        transaction.update(referencia, "recetas", hashRecetas)
                    }else{
                        transaction.update(referencia, "recetas", peliculaAGuardar)
                    }

                }
                .addOnSuccessListener {
                    Log.i("transaccion", "Transaccion Completa")
                }
                .addOnFailureListener{
                    Log.i("transaccion", "ERROR")
                }


        }else{
                        val RecetasGuardar = hashMapOf<String,Any?>()

                        db.collection("usuario").document(usuarioLocal.email.toString())
                            .set(

                                hashMapOf( "correo" to usuarioLocal.email.toString()
                                    ,"recetas" to RecetasGuardar)
                            )
                        db
                            .runTransaction { transaction ->
                                val documentoActual = transaction.get(referencia)
                                val hashPeliculas = documentoActual.get("recetas" +
                                        "") as HashMap<String,Any>
                                Log.i("transaccion", "hash: ${hashPeliculas}")

                                if(hashPeliculas != {}){
                                    hashPeliculas.put(receta.uidReceta.toString(), atributos)
                                    transaction.update(referencia, "recetas", hashPeliculas)
                                }else{
                                    transaction.update(referencia, "recetas", RecetasGuardar)
                                }

                            }
                            .addOnSuccessListener {
                                Log.i("transaccion", "Transaccion Completa")
                            }
                            .addOnFailureListener{
                                Log.i("transaccion", "ERROR")
                            }




                    }        }
                    }

    }

@SuppressLint("ResourceAsColor")
fun botonCambiar(holder: ViewHolder){
    if(flagBotton){

        holder.boton.setBackgroundColor(R.color.black)
        holder.boton.setText("AÑADIR")
        flagBotton =false
    }else{

        holder.boton.setBackgroundColor(R.color.verde)
        holder.boton.setText("GUARDADO")
        flagBotton =true
    }
}

}