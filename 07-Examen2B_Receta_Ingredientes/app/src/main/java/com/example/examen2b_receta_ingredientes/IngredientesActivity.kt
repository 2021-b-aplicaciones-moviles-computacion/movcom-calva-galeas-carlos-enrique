package com.example.examen2b_receta_ingredientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_receta_ingredientes.dto.DtoIngrediente
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IngredientesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)

        val recyclerViewIngrediente = findViewById<RecyclerView>(R.id.rvListaIngredientes)

        //     Botón para ir al Formulario de Registro de Ingredientes
        val btnIrAFormularioRegistroIngredientes = findViewById<Button>(
            R.id.btnInsertarIngrediente
        )

        btnIrAFormularioRegistroIngredientes.setOnClickListener {
            startActivity(Intent(this,IngredientresFormularioRegistro::class.java))
            this.finish()
        }

        var registroIngredientes: (MutableList<DocumentSnapshot>)
        var listaIngredientes = ArrayList<DtoIngrediente>()
        val db = Firebase.firestore

        val referenciaIngrediente = db.collection("ingrediente")

        referenciaIngrediente
            .get()
            .addOnSuccessListener {
                registroIngredientes = it.documents
                registroIngredientes.forEach{ iteracion ->
                    var objIngrediente = iteracion.toObject(DtoIngrediente::class.java)
                    objIngrediente!!.uid = iteracion.id
                    objIngrediente!!.calorias=iteracion.get("calorias").toString().toInt()
                    objIngrediente!!.precioUnitario=iteracion.get("precioUnitario").toString().toDouble()
                    objIngrediente!!.inventario=iteracion.get("inventario").toString().toBoolean()
                    objIngrediente!!.fechaCompra=iteracion.get("fechaCompra").toString()
                    objIngrediente!!.idReceta=iteracion.get("idReceta").toString()
                    objIngrediente!!.nombre=iteracion.get("nombre").toString()
                    listaIngredientes.add(objIngrediente)
                }
                //Iniciar Recycler View
                iniciarRecyclerView(listaIngredientes,this,recyclerViewIngrediente)
            }

    }
    fun iniciarRecyclerView(
        lista: ArrayList<DtoIngrediente>,
        activity: IngredientesActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = AdapterIngredientes(
            activity,
            // this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

    }

}