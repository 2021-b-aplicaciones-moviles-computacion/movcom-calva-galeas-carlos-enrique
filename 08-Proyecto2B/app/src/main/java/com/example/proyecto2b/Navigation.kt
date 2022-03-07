package com.example.proyecto2b

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyecto2b.databinding.ActivityNavigationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType{
    BASIC,
    GOOGLE
}
class Navigation : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        val bundle = intent.extras
        val correo  = bundle?.getString("email")
        val provider2  = bundle?.getString("provider")
        val db  = FirebaseFirestore.getInstance()

        val RecetaGuardar = hashMapOf<String,Any?>()

        val referencia = db.collection("usuario").document(correo.toString())
        referencia
            .get()
            .addOnSuccessListener {
                if(it["recetas"] !=null) {
                    val prueba =  it["recetas"]
                    Log.i("Prueba","${prueba}")

                    db.collection("usuario").document(correo.toString())
                        .set(
                            hashMapOf(
                                "correo" to correo.toString(),
                                "recetas" to RecetaGuardar

                            )
                        )


                }else{

                    db.collection("usuario").document(correo.toString())
                        .set(
                            hashMapOf(
                                "correo" to correo.toString(),
                                "recetas" to RecetaGuardar
                            )
                        )
                }                }




        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}

