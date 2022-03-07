package com.example.proyecto2b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

      setup()

    }


    fun setup(){
        val boton = findViewById<Button>(R.id.aicCrearCuenta)
        val usuario = findViewById<EditText>(R.id.aicetusuario)
        val contrasena = findViewById<EditText>(R.id.aicetcontraseña)
        val contrasena2= findViewById<EditText>(R.id.aicetcontraseña2)



        boton.setOnClickListener {
            if (usuario.text.isNotBlank() && contrasena.text.isNotBlank() && contrasena2.text.isNotBlank() ){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    usuario.text.toString(),
                    contrasena.text.toString()
                ).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email?: "", ProviderType.BASIC)
                    }else{
                        showAlter()
                    }
                }
            }else{
                showAlter()
            }
        }
    }
    fun showAlter(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error al registrar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, Navigation::class.java).apply {
            putExtra("email", email )
            putExtra("provider", provider.name)
        }

        startActivity(homeIntent)
    }



}