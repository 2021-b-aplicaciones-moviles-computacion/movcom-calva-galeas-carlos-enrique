package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.proyecto2b.R
import com.google.firebase.auth.FirebaseAuth

class Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

      setup()

    }


    fun setup(){
        val boton = findViewById<Button>(R.id.aiccrearCuenta)
        val usuario = findViewById<EditText>(R.id.ezfoodusuario)
        val contasena = findViewById<EditText>(R.id.ezfoodcontraseña)



        boton.setOnClickListener {
            if (usuario.text.isNotBlank() && contasena.text.isNotBlank() ){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    usuario.text.toString(),
                    contasena.text.toString()
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
        builder.setMessage("Se ha producido un error almomento de registrar al usuario, verifique que al contraseña sea la misma en los dos campos")
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