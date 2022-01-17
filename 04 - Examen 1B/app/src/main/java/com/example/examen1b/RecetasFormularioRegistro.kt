package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RecetasFormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas_formulario_registro)
        BaseDeDatos.TablaReceta = SQLiteHelper(this)

        //Ingreso de datos
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreReceta)
        val editTextTipoReceta = findViewById<EditText>(R.id.edtxtTipoReceta)
        val editTextNumeroIngredientes = findViewById<EditText>(R.id.edtxtNumeroIngredientesReceta)
        val editTextPrecio = findViewById<EditText>(R.id.edtxtPrecioReceta)
        val editTexTiempoPreparacion = findViewById<EditText>(R.id.edtxtTiempoPreparacionReceta)

        //Boton para crear
        val btnCrearReceta = findViewById<Button>(R.id.btnCrearReceta)


        btnCrearReceta.setOnClickListener{
            if(BaseDeDatos.TablaReceta != null){
                BaseDeDatos.TablaReceta!!.crearRecetaFormulario(
                    editTextNombre.text.toString(),
                    editTextTipoReceta.text.toString(),
                    editTextNumeroIngredientes.text.toString().toInt(),
                    editTextPrecio.text.toString().toDouble(),
                    editTexTiempoPreparacion.text.toString().toInt()
                )
                    abrirActividad(RecetasActivity::class.java)
                val toast = Toast.makeText(this, "Receta creada exitosamente", Toast.LENGTH_SHORT).show()
                Log.i("bdd", "Receta creada")
                this.finish()
            }
        }

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
}