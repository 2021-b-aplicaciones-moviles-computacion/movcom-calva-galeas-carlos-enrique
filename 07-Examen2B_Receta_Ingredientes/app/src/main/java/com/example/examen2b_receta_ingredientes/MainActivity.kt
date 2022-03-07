package com.example.examen2b_receta_ingredientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //     Botón para ir a la Actividad MateriasActivity

        val btnIrRecetasActivity = findViewById<Button>(
            R.id.btnIrARecetas
        )

        btnIrRecetasActivity.setOnClickListener{
            abrirActividad(RecetasActivity::class.java)
        }

        //     Botón para ir a la Actividad EstudiantesActivity

        val btnIrIngredientesActivity = findViewById<Button>(
            R.id.btnIrAIngredientes
        )

        btnIrIngredientesActivity.setOnClickListener{
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