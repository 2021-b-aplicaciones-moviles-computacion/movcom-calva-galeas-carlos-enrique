package com.example.proyecto2b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonIniciarSesion = findViewById<Button>(R.id.btn_iniciar_sesion)
        botonIniciarSesion.setOnClickListener {
            abrirActividad(IniciarSesion::class.java)
        }

        val botonRegistrarse = findViewById<Button>(R.id.btn_registrarse)
        botonRegistrarse.setOnClickListener {
            abrirActividad(RegistrarsePor::class.java)
        }
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}