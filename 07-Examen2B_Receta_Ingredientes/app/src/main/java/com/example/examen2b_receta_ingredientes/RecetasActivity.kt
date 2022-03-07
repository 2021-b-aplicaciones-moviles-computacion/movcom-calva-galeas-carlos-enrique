package com.example.examen2b_receta_ingredientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_receta_ingredientes.dto.DtoReceta
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecetasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas)

        val recyclerViewReceta = findViewById<RecyclerView>(R.id.rvListaRecetas)

        //     Botón para ir al Formulario de Registro de Recetas
        val btnIrAFormularioRegistroRecetas = findViewById<Button>(
            R.id.btnInsertarRecetas
        )

        btnIrAFormularioRegistroRecetas.setOnClickListener{
            abrirActividad(RecetasFormularioRegistro::class.java)
            this.finish()
        }

        var registroReceta: (MutableList<DocumentSnapshot>)

        val listaReceta = ArrayList<DtoReceta>()

        val db = Firebase.firestore

        val referenciaReceta = db.collection("receta")

        referenciaReceta
            .get()
            .addOnSuccessListener {
                registroReceta = it.documents
                registroReceta.forEach{ iteracion ->
                    val objReceta = iteracion.toObject(DtoReceta::class.java)
                    objReceta!!.uid = iteracion.id
                    objReceta.nombre = iteracion.get("nombre").toString()
                    objReceta.tipoReceta = iteracion.get("tipoReceta").toString()
                    objReceta.numeroIngredientes = iteracion.get("numeroIngredientes").toString().toInt()
                    objReceta.precio = iteracion.get("precio").toString().toDouble()
                    objReceta.tiempoPreparacion = iteracion.get("tiempoPreparacion").toString().toInt()

                    listaReceta.add(objReceta)
                }
                //Iniciar Recycler View
                iniciarRecyclerView(listaReceta,this,recyclerViewReceta)
            }


    }
    fun iniciarRecyclerView(
        lista: ArrayList<DtoReceta>,
        activity: RecetasActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = AdapterRecetas(
            activity,
            lista,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

        //registerForContextMenu(recyclerView)



    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito)
    }
}