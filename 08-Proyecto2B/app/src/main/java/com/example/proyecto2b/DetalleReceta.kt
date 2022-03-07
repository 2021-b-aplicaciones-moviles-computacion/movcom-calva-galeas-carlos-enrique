package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto2b.R
import com.example.proyecto2b.ui.adaptadores.AdaptadorCategoria
import com.example.proyecto2b.ui.clases.Categoria
import com.example.proyecto2b.ui.clases.RecetaFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DetalleReceta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta)





        val Receta = intent.getSerializableExtra("receta") as RecetaFirebase

        val storage = Firebase.storage
        var imagenRefPortada = storage.getReferenceFromUrl(Receta.imagenReceta.toString())
        var imagen = findViewById<ImageView>(R.id.daIvImagen)
        Glide.with(this)
            .load(imagenRefPortada)
            .into(imagen)
        val db = Firebase.firestore
        val peliculasInicio = db.collection("Receta").document(Receta.uidReceta.toString())
        Log.e("help1","uid: ${Receta.uidReceta.toString()}")
        peliculasInicio
            .get()
            .addOnSuccessListener {
                Receta.IdYouTube=it["IdYouTube"].toString()
                Receta.categorias=it["categorias"].toString()
                Receta.imagenVideoReceta=it["portada"].toString()
                Receta.autor=null
                Receta.ingredientes=it["ingredientes"].toString()


                //Categoria
                val TiposRecetas= ArrayList<Categoria>()
                var hashCategoria = it["categorias"] as HashMap<String,Any>
                hashCategoria.forEach{
                    TiposRecetas.add(Categoria(it.value.toString()))
                }

                var recetas1 = findViewById<RecyclerView>(R.id.dpRvCategoria)
                recetas1.adapter = AdaptadorCategoria(TiposRecetas, this)
                recetas1.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false)




                var imagenRefTrailer = storage.getReferenceFromUrl(Receta.imagenVideoReceta.toString())

                var portada = findViewById<ImageView>(R.id.dpivportada)
                Glide.with(this)
                    .load(imagenRefTrailer)
                    .into(portada)
                var NombreReceta = findViewById<TextView>(R.id.dpTvNombreReceta)
                NombreReceta.setText(Receta.nombre)
                var descripcion = findViewById<TextView>(R.id.dpTvDescripcion)
                descripcion.setText(Receta.descripcion)
                var tiempoPreparacion = findViewById<TextView>(R.id.dpTvTiempoPreparacion)
                tiempoPreparacion.setText(Receta.tiempoPreparacion)
                var ingredientes = findViewById<TextView>(R.id.dpTvIngredientes)
                ingredientes.setText(Receta.ingredientes)
                var calificacion = findViewById<TextView>(R.id.dpTvCalificacion)
                calificacion.setText(Receta.calificacion.toString())

                var categorias = Receta.categorias
                Log.i("help","${categorias}")


                var portadaBoton = findViewById<ImageView>(R.id.dpivportada)
                portadaBoton.setOnClickListener {
                    startActivity(Intent(this, videoYoutube2::class.java).putExtra("youTubeID",Receta.IdYouTube) )
                }

            }
            .addOnFailureListener{

            }

        val botonAnadir =findViewById<Button>(R.id.button2)
        botonAnadir.setOnClickListener {

            val atributos = hashMapOf<String,String>(
                "descripcion" to Receta.descripcion.toString(),
                "calificacion" to Receta.calificacion.toString(),
                "tiempoPreparacion" to Receta.tiempoPreparacion.toString(),
                "imagen" to Receta.imagenReceta.toString(),
                "nombre" to Receta.nombre.toString(),
                "Ingredientes" to Receta.ingredientes.toString(),
                "uidReceta" to Receta.uidReceta.toString()
            )

            val peliculaAGuardar = hashMapOf<String,HashMap<String,String>>(
                Receta.uidReceta.toString() to atributos
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
                                val hashRecetas =
                                    documentoActual.get("recetas") as HashMap<String, Any>
                                Log.i("transaccion", "hash: ${hashRecetas}")

                                if (hashRecetas != {}) {
                                    hashRecetas.put(Receta.uidReceta.toString(), atributos)
                                    transaction.update(referencia, "recetas", hashRecetas)
                                } else {
                                    transaction.update(referencia, "recetas", peliculaAGuardar)
                                }

                            }
                            .addOnSuccessListener {
                                Log.i("transaccion", "Transaccion Completa")
                            }
                            .addOnFailureListener {
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
                                val hashPeliculas = documentoActual.get("recetas") as HashMap<String,Any>
                                Log.i("transaccion", "hash: ${hashPeliculas}")

                                if(hashPeliculas != {}){
                                    hashPeliculas.put(Receta.uidReceta.toString(), atributos)
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

                    }                    }
        }



    }





}