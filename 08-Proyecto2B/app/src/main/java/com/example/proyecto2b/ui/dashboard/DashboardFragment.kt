package com.example.proyecto2b.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.R
import com.example.proyecto2b.databinding.FragmentDashboardBinding
import com.example.proyecto2b.ui.adaptadores.AdaptadorRecetaBusqueda
import com.example.proyecto2b.ui.clases.RecetaFirebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private var sear: SearchView ?=null
    private  var RecetasMasBuscadas= ArrayList<RecetaFirebase>()


    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val context = container!!.context
        var recyclerReceta = root.findViewById<RecyclerView>(R.id.fdrvbusquedarecetas)


        recyclerReceta.layoutManager = LinearLayoutManager(context)
        // recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))


        val db = Firebase.firestore
        val recetasInicio = db.collection("Receta")

        recetasInicio
            .get()
            .addOnSuccessListener {
                for(receta in it){
                    RecetasMasBuscadas.add(
                        RecetaFirebase(
                            receta["uidReceta"].toString(),
                            null,
                            receta["descripcion"].toString(),
                            receta["calificacion"].toString().toDouble(),
                            null,
                            receta["tiempoPreparacion"].toString().toInt(),
                            receta["nombre"].toString(),
                            receta["imagen"].toString(),
                            null,
                            receta["autor"].toString(),
                            receta["ingredientes"].toString(),
                        )
                    )

                }

                recyclerReceta.adapter = AdaptadorRecetaBusqueda(RecetasMasBuscadas,context)
            }
            .addOnFailureListener{

            }


        var buscador = root.findViewById<SearchView>(R.id.sv_buscador)
        buscador.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Receta.clear()
                Log.i("search","query: ${query}")
                var listaRecetas = ArrayList<RecetaFirebase>()

                val db = Firebase.firestore
                val RecetasInicio = db.collection("Receta")

                RecetasInicio
                    .whereEqualTo("nombre", query)
                    //.whereArrayContains("nombre",query!!)
                    .get()
                    .addOnSuccessListener {
                        for(receta in it){
                            listaRecetas.add(
                                RecetaFirebase(
                                    receta["uidReceta"].toString(),
                                    null,
                                    receta["descripcion"].toString(),
                                    receta["calificacion"].toString().toDouble(),
                                    null,
                                    receta["tiempoPreparacion"].toString().toInt(),
                                    receta["nombre"].toString(),
                                    receta["imagen"].toString(),
                                    null,
                                    receta["autor"].toString(),
                                    receta["ingredientes"].toString(),
                                )
                            )
                            Log.i("search","receta uid: ${receta["uidReceta"].toString()}")
                        }
                        recyclerReceta.adapter = AdaptadorRecetaBusqueda(listaRecetas,context)
                    }
                    .addOnFailureListener{
                        Log.i("search","Error: ${it}")
                    }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText == ""){
                    recyclerReceta.adapter = AdaptadorRecetaBusqueda(RecetasMasBuscadas,context)
                }
                return true
            }
        })





        return root
    }


}