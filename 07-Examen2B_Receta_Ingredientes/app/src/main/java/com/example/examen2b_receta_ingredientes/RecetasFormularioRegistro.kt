package com.example.examen2b_receta_ingredientes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecetasFormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas_registro)

        //Boton para crear
        val btnCrearReceta = findViewById<Button>(R.id.btnCrearReceta)

        btnCrearReceta.setOnClickListener {
            crearReceta()
        }

    }

    private fun crearReceta(){
        //Ingreso de datos

        val nombreText = findViewById<EditText>(R.id.edtxtNombreReceta)
        val tipoRecetaText = findViewById<EditText>(R.id.edtxtTipoReceta)
        val numeroIngredientesText = findViewById<EditText>(R.id.edtxtNumeroIngredientes)
        val precioText = findViewById<EditText>(R.id.edtxtPrecio)
        val tiempoPreparacionText = findViewById<EditText>(R.id.edtxtTiempoPreparacion)

        val nuevaReceta = hashMapOf<String,Any>(
            "nombre" to nombreText.text.toString(),
            "tipoReceta"  to tipoRecetaText.text.toString(),
            "numeroIngredientes" to numeroIngredientesText.text.toString().toInt(),
            "precio" to precioText.text.toString().toDouble(),
            "tiempoPreparacion" to tiempoPreparacionText.text.toString().toInt()
        )

        val db = Firebase.firestore
        val referenciaReceta = db.collection("receta")
        referenciaReceta.add(nuevaReceta).addOnSuccessListener {
            nombreText.text.clear()
            tipoRecetaText.text.clear()
            numeroIngredientesText.text.clear()
            precioText.text.clear()
            tiempoPreparacionText.text.clear()
            Toast.makeText(
                this,
                "Receta ingresada con éxito",
                Toast.LENGTH_SHORT
            ).show()
            this.finish()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "El registro no pudo ser creado",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}