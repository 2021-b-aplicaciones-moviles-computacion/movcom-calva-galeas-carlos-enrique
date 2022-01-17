package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RecetasFormularioActualizacion : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas_formulario_actualizacion)

        //val itemId = intent.getSerializableExtra("id") as? Materia
        val itemId = intent.getParcelableExtra<Receta>("id")

        Log.i("bdd", "${itemId?.id}")
        BaseDeDatos.TablaReceta = SQLiteHelper(this)

        //Ingreso de datos


        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreRecetaActualizar)
        val editTextTipoReceta = findViewById<EditText>(R.id.edtxtTipoRecetaActualizar)
        val editTextNumeroIngredientes = findViewById<EditText>(R.id.edtxtNumeroIngredientesRecetactualizar)
        val editTextPrecio = findViewById<EditText>(R.id.edtxtPrecioRecetaActualizar)
        val editTextTiempoPreparacion = findViewById<EditText>(R.id.edtxtTiempoPreparacionRecetaActualizar)




        editTextNombre.setText(itemId!!.nombre)
        editTextTipoReceta.setText(itemId!!.tipoReceta)
        editTextNumeroIngredientes.setText(itemId!!.numeroIngredientes.toString())
        editTextPrecio.setText(itemId!!.precio.toString())
        editTextTiempoPreparacion.setText(itemId!!.tiempoPreparacion.toString())

        //Boton para Actualizar
        val btnActualizarReceta = findViewById<Button>(R.id.btnActualizarReceta)


        btnActualizarReceta.setOnClickListener {
            if (BaseDeDatos.TablaReceta != null) {
                BaseDeDatos.TablaReceta!!.actualizarRecetaFormulario(
                    itemId!!.id,
                    editTextNombre.text.toString(),
                    editTextTipoReceta.text.toString(),
                    editTextNumeroIngredientes.text.toString().toInt(),
                    editTextPrecio.text.toString().toDouble(),
                    editTextTiempoPreparacion.text.toString().toInt()
                )

                abrirActividad(RecetasActivity::class.java)
                Toast.makeText(this, "Receta actualizada exitosamente", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Receta actualizada")
                this.finish()
            }
        }

    }


    fun abrirActividad(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito);
    }
}