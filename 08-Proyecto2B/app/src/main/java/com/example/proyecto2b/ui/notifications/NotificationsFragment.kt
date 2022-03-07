package com.example.proyecto2b.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.MainActivity
import com.example.proyecto2b.R
import com.example.proyecto2b.databinding.FragmentNotificationsBinding
import com.example.proyecto2b.ui.adaptadores.AdaptadorRecetaMiPerfil
import com.example.proyecto2b.ui.clases.RecetaFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

       var context = container!!.context

        var recyclerPeliculaGuardada = root.findViewById<RecyclerView>(R.id.dnRvReceta)
        recyclerPeliculaGuardada.layoutManager = LinearLayoutManager(context)



        var botonSlir = root.findViewById<Button>(R.id.mpBtsalir)
        botonSlir.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
           startActivity(Intent(activity, MainActivity::class.java))

        }



        var arrayPeliculas = ArrayList<RecetaFirebase>()

        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser

        val db = Firebase.firestore

        Log.i("transaccion", "User : ${usuarioLocal!!.email}")
        val referencia = db
            .collection("usuario").document(usuarioLocal!!.email.toString())



        referencia
            .get()
            .addOnSuccessListener {
                if(it["recetas"] !=null) {
                    var hasRecetas = it["recetas"] as HashMap<String, HashMap<String, String>>

                    for ((key, value) in hasRecetas) {
                        arrayPeliculas.add(
                            RecetaFirebase(
                                value["uid_Receta"].toString(),
                                null,
                                value["descripcion"].toString(),
                                value["calificacion"].toString().toDouble(),
                                null,
                                value["tiempoPreparacion"].toString(),
                                value["nombre"].toString(),
                                value["imagen"].toString(),
                                null,
                                value["autor"].toString(),
                                value["ingredientes"].toString(),
                            )
                        )
                    }
                }else{



                }
                Log.i("HelpUser","array: ${arrayPeliculas}")



                recyclerPeliculaGuardada.adapter = AdaptadorRecetaMiPerfil(arrayPeliculas,context)


            }
            .addOnFailureListener{

            }



        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


