package com.example.examen1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btnIrMateriasActivity = findViewById<Button>(
            R.id.btnIrARecetas
        )

        btnIrMateriasActivity.setOnClickListener{
            abrirActividad(RecetasActivity::class.java)
        }


        val btnIrEstudiantesActivity = findViewById<Button>(
            R.id.btnIrAIngredientes
        )

        btnIrEstudiantesActivity.setOnClickListener{
            abrirActividad(IngredientesActivity::class.java)
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