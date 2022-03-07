package com.example.examen2b_receta_ingredientes

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IngredientresFormularioRegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes_formulario_registro)

        //ciudades


        //Botón para crear
        val btnCrearIngrediente = findViewById<Button>(R.id.btnCrearIngrediente)

        btnCrearIngrediente.setOnClickListener {
            crearIngrediente()
        }


    }

    private fun crearIngrediente() {


        //Ingreso de datos

        val editTextPrecioUnitario = findViewById<EditText>(R.id.edtxtPrecioUnitarioIngrediente)
        val editTextCalorias = findViewById<EditText>(R.id.edtxtCaloriasIngrediente)
        val checkBoxInventario = findViewById<CheckBox>(R.id.checkBoxInventario)
        val editTextFechaCompra = findViewById<EditText>(R.id.edtxtfechaCompraIngredientes)
        val editTextNombreReceta = findViewById<EditText>(R.id.edtxtNombreRecetaIngrediente)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreIngrediente)


        val db = Firebase.firestore
        val referenciaReceta = db.collection("receta")
        referenciaReceta
            .whereEqualTo(
                "nombre",
                editTextNombreReceta.text.toString()
            )
            .get()
            .addOnSuccessListener {
                for (documentos in it) {
                    Log.i(" bdd", "${documentos.data}")
                    Log.i(" bdd", "${documentos.id}")

                    val nuevoIngrediente = hashMapOf<String, Any>(
                        "precioUnitario" to editTextPrecioUnitario.text.toString().toDouble(),
                        "calorias" to editTextCalorias.text.toString().toInt(),
                        "inventario" to checkBoxInventario.isChecked,
                        "fechaCompra" to editTextFechaCompra.text.toString(),
                        "idReceta" to documentos.id,
                        "nombre" to editTextNombre.text.toString(),

                    )


                    val referenciaIngrediente = db.collection("ingrediente")
                    referenciaIngrediente.add(nuevoIngrediente).addOnSuccessListener {
                        editTextPrecioUnitario.text.clear()
                        editTextCalorias.text.clear()
                        editTextFechaCompra.text.clear()
                        editTextNombreReceta.text.clear()
                        editTextNombre.text.clear()
                        Toast.makeText(
                            this,
                            "Ingrediente ingresada con éxito",
                            Toast.LENGTH_SHORT
                        ).show()
                        this.finish()
                    }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "No se puedo completar el registro",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)

            }


    }
}