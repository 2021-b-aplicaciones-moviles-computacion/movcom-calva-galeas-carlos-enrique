package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class IngredientesFormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes_formulario_registro)
        BaseDeDatos.TablaReceta = SQLiteHelper(this)
        BaseDeDatos.TablaIngrediente = SQLiteHelper(this)
        var RecetaEncontrada = Receta(0, "", "", 0, 0.0, 0)

        //Ingreso de datos



        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreIngrediente)
        val editTextCalorias = findViewById<EditText>(R.id.edtxtCaloriasIngrediente)
        val editTextFechaCompra = findViewById<EditText>(R.id.edtxtfechaCompraIngrediente)
        val editTextNombreReceta = findViewById<EditText>(R.id.edtxtIDRecetaIngrediente)
        val checkBoxInventario = findViewById<CheckBox>(R.id.checkBoxInventarioIngrediente)
        val editTextPrecioUnitario = findViewById<EditText>(R.id.edtxtPrecioUnitarioIngrediente)


        //Botón para crear
        val btnCrearIngrediente = findViewById<Button>(R.id.btnCrearIngrediente)
        btnCrearIngrediente.setOnClickListener {
            val busquedaReceta: String = editTextNombreReceta.text.toString()
            if (BaseDeDatos.TablaReceta != null) {
                RecetaEncontrada =
                    BaseDeDatos.TablaReceta!!.consultarRecetaPorNombre(busquedaReceta)
            }
            if (BaseDeDatos.TablaIngrediente != null) {
                BaseDeDatos.TablaIngrediente!!.crearIngredienteFormulario(
                    RecetaEncontrada.id,
                    editTextNombre.text.toString(),
                    editTextCalorias.text.toString().toInt(),
                    editTextFechaCompra.text.toString(),
                    checkBoxInventario.isChecked,
                    editTextPrecioUnitario.text.toString().toDouble()

                )
                abrirActividad(IngredientesActivity::class.java)
                val toast = Toast.makeText(this, "Ingrediente creado exitosamente", Toast.LENGTH_SHORT).show()
                Log.i("bdd", "Ingrediente creado")
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