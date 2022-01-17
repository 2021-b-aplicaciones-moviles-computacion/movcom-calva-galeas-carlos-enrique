package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class RecetasActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas)

        var listaReceta = arrayListOf<Receta>()

        BaseDeDatos.TablaReceta = SQLiteHelper(this)
        if (BaseDeDatos.TablaReceta != null) {
            listaReceta = BaseDeDatos.TablaReceta!!.consultaRecetaTotal()
        }



        val recyclerViewReceta = findViewById<RecyclerView>(R.id.rvListaRecetas)

        //Iniciar Recycler View
        iniciarRecyclerView(listaReceta,this,recyclerViewReceta)

        //     Botón para ir al Formulario de Registro de Receta
        val btnIrAFormularioRegistroRecetas = findViewById<Button>(
            R.id.btnInsertarRecetas
        )

        btnIrAFormularioRegistroRecetas.setOnClickListener{
            abrirActividad(RecetasFormularioRegistro::class.java)
            this.finish()
        }


    }

    fun iniciarRecyclerView(
        lista: ArrayList<Receta>,
        activity: RecetasActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = AdapterReceta(
            activity,
           // this,
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
        this.startActivity(intentExplicito);
    }


    fun abrirActividadConParametros(
        clase: Class<*>,
        receta: Receta
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("receta", receta)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

}















