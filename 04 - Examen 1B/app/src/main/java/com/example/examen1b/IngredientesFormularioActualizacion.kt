package com.example.examen1b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class IngredientesFormularioActualizacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes_formulario_actualizacion)

        val itemId = intent.getParcelableExtra<Ingrediente>("id")
        Log.i("bdd", "${itemId?.id}")

        BaseDeDatos.TablaIngrediente = SQLiteHelper(this)
        BaseDeDatos.TablaReceta = SQLiteHelper(this)
        var RecetaEncontrada = Receta(0, "", "", 0, 0.0, 0)
        //Ingreso de datos
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreIngredienteActualizar)
        val editTextCalorias = findViewById<EditText>(R.id.edtxtCaloriasIngredienteActualizar)
        val editTextFechaCompra =
            findViewById<EditText>(R.id.edtxtfechaCompraIngredienteActualizar)
        val checkBoxInventario =
            findViewById<CheckBox>(R.id.checkBoxInventarioIngredienteActualizar)
        val editTextPrecioUnitario = findViewById<EditText>(R.id.edtxtPrecioUnitarioIngredienteActualizar)
        val editTextNombreReceta = findViewById<EditText>(R.id.edtxtIDRecetaIngredienteActualizar)

        editTextNombre.setText(itemId!!.nombre)
        editTextCalorias.setText(itemId!!.calorias.toString())
        editTextPrecioUnitario.setText(itemId!!.precioUnitario.toString())
        editTextFechaCompra.setText(itemId!!.fechaCompra)
        checkBoxInventario.isChecked = itemId!!.inventario == true


        if (BaseDeDatos.TablaReceta != null) {
            RecetaEncontrada =
                BaseDeDatos.TablaReceta!!.consultarRecetaPorId(itemId!!.idReceta)
        }

        editTextNombreReceta.setText( RecetaEncontrada.nombre.toString())

//        Boton para Actualizar

        val btnActualizarIngrediente = findViewById<Button>(R.id.btnActualizarIngrediente)

        btnActualizarIngrediente.setOnClickListener {

            if (BaseDeDatos.TablaIngrediente != null) {
                BaseDeDatos.TablaIngrediente!!.actualizarIngredienteFormulario(
                    itemId!!.id,
                    RecetaEncontrada.id,
                    editTextNombre.text.toString(),
                    editTextCalorias.text.toString().toInt(),
                    editTextFechaCompra.text.toString(),
                    checkBoxInventario.isChecked,
                    editTextPrecioUnitario.text.toString().toDouble(),
                )

                Toast.makeText(this, "Ingrediente actualizado exitosamente", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Ingrediente actualizado")
                this.finish()

            }
        }


    }
}