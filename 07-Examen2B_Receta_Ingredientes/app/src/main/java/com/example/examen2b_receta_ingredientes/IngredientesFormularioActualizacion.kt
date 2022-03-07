package com.example.examen2b_receta_ingredientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.examen2b_receta_ingredientes.dto.DtoIngrediente
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IngredientesFormularioActualizacion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes_formulario_actualizacion)



        val itemId = intent.getParcelableExtra<DtoIngrediente>("id")

        Log.i("bdd", "${itemId?.uid}")
        Log.i("bdd", "${itemId?.nombre}")




        val db = Firebase.firestore
        val referenciaReceta = db.collection("receta")
        val referenciaIngrediente = db.collection("ingrediente")


        //Ingreso de datos

        val editTextCalorias = findViewById<EditText>(R.id.edtxtCaloriasIngredienteActualizar)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreIngredienteActualizar)
        val editTextPrecioUnitario = findViewById<EditText>(R.id.edtxtPrecioUnitarioActualizar)
        val editTextFechaCompra =
            findViewById<EditText>(R.id.edtxtfechaCompraIngredienteActualizar)
        val checkBoxInventario =
            findViewById<CheckBox>(R.id.checkBoxInventarioIngredienteActualizar)
        val editTextNombreReceta = findViewById<EditText>(R.id.edtxtNombreRecetaIngredienteActualizar)
        var RecetaUid =""

        editTextCalorias.setText(itemId!!.calorias.toString())
        editTextNombre.setText(itemId!!.nombre)
        editTextPrecioUnitario.setText(itemId!!.precioUnitario.toString())
        editTextFechaCompra.setText(itemId!!.fechaCompra)
        checkBoxInventario.isChecked = itemId!!.inventario == true


        referenciaReceta
            .document(itemId.idReceta.toString())
            .get()
            .addOnSuccessListener {document->
                editTextNombreReceta.setText(document.data?.get("nombre").toString())
                RecetaUid = document.id
            }

        //        Boton para Actualizar

        val btnActualizarIngrediente = findViewById<Button>(R.id.btnActualizarIngrediente)

        btnActualizarIngrediente.setOnClickListener{
            if (referenciaIngrediente!=null){
                referenciaIngrediente.document(itemId.uid.toString()).update(
                    "precioUnitario",editTextPrecioUnitario.text.toString().toDouble(),
                    "calorias",editTextCalorias.text.toString().toInt(),
                    "inventario",checkBoxInventario.isChecked,
                    "fechaCompra",editTextFechaCompra.text.toString(),
                    "idReceta",RecetaUid,
                    "nombre",editTextNombre.text.toString(),

                )
                abrirActividad(IngredientesActivity::class.java)
                Toast.makeText(this, "Ingrediente actualizado exitosamente", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Ingrediente actualizado")
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