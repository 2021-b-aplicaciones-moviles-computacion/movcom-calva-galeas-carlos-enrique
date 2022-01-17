package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class IngredientesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)

        var listaIngrediente = arrayListOf<Ingrediente>()
        BaseDeDatos.TablaIngrediente = SQLiteHelper(this)
        if (BaseDeDatos.TablaIngrediente != null) {
            listaIngrediente = BaseDeDatos.TablaIngrediente!!.consultaListaIngredientes()
        }



        val recyclerViewIngredientes = findViewById<RecyclerView>(R.id.rvListaIngrediente)

        //Iniciar Recycler View
        iniciarRecyclerView(listaIngrediente,this,recyclerViewIngredientes)

        //     Botón para ir al Formulario de Registro de Ingredientes
        val btnIrAFormularioRegistroIngrediente = findViewById<Button>(
            R.id.btnInsertarIngrediente
        )

        btnIrAFormularioRegistroIngrediente.setOnClickListener {
            startActivity(Intent(this, IngredientesFormularioRegistro::class.java))
            this.finish()
        }
    }


    fun iniciarRecyclerView(
        lista: ArrayList<Ingrediente>,
        activity: IngredientesActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = AdapterIngrediente(
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