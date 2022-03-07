package com.example.examen2b_receta_ingredientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen2b_receta_ingredientes.dto.DtoReceta
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecetasFormularioActualizacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas__actualizacion)

        val itemId = intent.getParcelableExtra<DtoReceta>("id")

        Log.i("bdd", "${itemId?.uid}")

        val db = Firebase.firestore
        val referenciaReceta = db.collection("receta")

        //Ingreso de datos

        val editnombreText = findViewById<EditText>(R.id.edtxtNombreRecetaActualizar)
        val edittipoRecetaText = findViewById<EditText>(R.id.edtxtTipoRecetaActualizar)
        val editnumeroIngredientesText = findViewById<EditText>(R.id.edtxtNumeroIngredientesActualizar)
        val editprecioText = findViewById<EditText>(R.id.edtxtPrecioActualizar)
        val edittiempoPreparacionText = findViewById<EditText>(R.id.edtxtTiempoPreparacionActualizar)


        editnombreText.setText(itemId!!.nombre)
        edittipoRecetaText.setText(itemId.tipoReceta)
        editnumeroIngredientesText.setText(itemId.numeroIngredientes.toString())
        editprecioText.setText(itemId.precio.toString())
        edittiempoPreparacionText.setText(itemId.tiempoPreparacion.toString())

        //Boton para Actualizar
        val btnActualizarReceta = findViewById<Button>(R.id.btnActualizarReceta)

        btnActualizarReceta.setOnClickListener {


            if (referenciaReceta != null) {
                referenciaReceta.document(itemId.uid.toString()).update(
                    "nombre",editnombreText.text.toString(),
                    "tipoReceta",edittipoRecetaText.text.toString(),
                    "numeroIngredientes", editnumeroIngredientesText.text.toString().toInt(),
                    "precio", editprecioText.text.toString().toDouble(),
                    "tiempoPreparacion",  edittiempoPreparacionText.text.toString().toInt()
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