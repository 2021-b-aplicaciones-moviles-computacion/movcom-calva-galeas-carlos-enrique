package com.example.proyecto2b.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto2b.R
import com.example.proyecto2b.databinding.FragmentHomeBinding
import com.example.proyecto2b.ui.adaptadores.AdaptadorReceta
import com.example.proyecto2b.ui.adaptadores.ViewPagerAdapter2
import com.example.proyecto2b.ui.clases.Receta
import com.example.proyecto2b.ui.clases.RecetaFirebase
import com.example.proyecto2b.ui.clases.Slide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val context = container!!.context



        ////Slider

        var recyclerSlider = root.findViewById<RecyclerView>(R.id.rvSlider2)


        recyclerSlider.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        // recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recyclerSlider.adapter = ViewPagerAdapter2(generarSlider(),context)

        //Recetas


        ////Recetas
        var recyclerReceta = root.findViewById<RecyclerView>(R.id.home_rv_recetas)
        var recyclerReceta2 = root.findViewById<RecyclerView>(R.id.home_rv_recetas2)
        var recyclerReceta3 = root.findViewById<RecyclerView>(R.id.home_rv_recetas3)
        recyclerReceta.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        recyclerReceta2.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        recyclerReceta3.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        // recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        var listaRecetas = ArrayList<RecetaFirebase>()

        val db = Firebase.firestore
        val RecetasInicio = db.collection("Receta")

        RecetasInicio
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
                            receta["tiempPreparacion"].toString(),
                            receta["nombre"].toString(),
                            receta["imagen"].toString(),
                          null,
                            receta["autor"].toString(),
                            receta["ingredientes"].toString(),

                        )
                    )

                }

                recyclerReceta.adapter = AdaptadorReceta(listaRecetas,context)
                recyclerReceta2.adapter = AdaptadorReceta(listaRecetas,context)
                recyclerReceta3.adapter = AdaptadorReceta(listaRecetas,context)
            }
            .addOnFailureListener{

            }

        return root
    }


    private fun generarRecetas ():ArrayList<Receta>{
        var lista = ArrayList<Receta>()

        //Seco de Pollo
        var categoriasReceta1 = ArrayList<String>()
        categoriasReceta1.add("Seco")
        categoriasReceta1.add("Segundo")

        lista.add(
            Receta(
                R.drawable.receta_portada_secopollo,
                R.drawable.receta_video_secopollo,
                "Seco de pollo",
                "8.9",
                "Seco de pollo Ecuatoriano , facil de preparar",
                "5 piezas de pollo variadas\n" +
                        "2 Cucharaditas Comino Molido\n" +
                        "2 cucharaditas de Achiote en polvo\n" +
                        "3 cucharadas de Aceite vegetal\n" +
                        "2 Unidades de Pimiento Rojo, picado en cubos\n" +
                        "1 Cebolla perla mediana, picada en cubos\n" +
                        "2 Tomates, pelados y cortados en rodajas\n" +
                        "5 dientes de Ajo machacados\n" +
                        "2 Cubos Caldo De Gallina Maggi®\n" +
                        "1 Cucharada Cilantro, picado\n" +
                        "2 Tazas de Cerveza Rubia\n" +
                        "3/4 de Taza de Jugo de Naranjilla\n" +
                        "1 Aji Criollo\n" +
                        "1/4 Taza Cilantro\n" +
                        "1/4 Taza Perejil Fresco\n" +
                        "2 Cucharaditas Orégano seco NATURE'S HEART®\n" +
                        "Sal y pimienta al gusto",
                " 40 minutos ",
                categoriasReceta1,
                "Juan Ortiz",



                )
        )

        //Batido de fresa
        var categoriasReceta2 = ArrayList<String>()
        categoriasReceta2.add("Batido")
        categoriasReceta2.add("Desayuno")
        categoriasReceta2.add("Postre")
        lista.add(
            Receta(
                R.drawable.receta_portada_batidofresa,
                R.drawable.receta_video_batidofresa,
                "Batido de fresa",
                "8.6",
                "Batido de fresa , facil y rapido de preparar",
                "1 Taza Frutillas Bien lavadas\n" +
                        "2 Unidades Durazno En Almíbar\n" +
                        "2 Cucharadas Azúcar\n" +
                        "2 Tazas Leche Svelty® Total Move Deslactosada",
                "7 minutos ",
                categoriasReceta2,
                "Luis Gonzales",
            )
        )


        //Ensalada César
        var categoriasReceta3 = ArrayList<String>()
        categoriasReceta3.add("Ensalada")
        categoriasReceta3.add("Guarnicion>")

        lista.add(
            Receta(
                R.drawable.receta_portada_ensaladacesar,
                R.drawable.receta_video_ensaladacesar,
                "Ensalada César",
                "9.0",
                "Ensala César para acompañar tus comidas",
                "2 Tajadas Pan De Sanduche Cortado en cubitos\n" +
                        "1 Taza Filetes De Pechuga De Pollo\n" +
                        "1 Sobre Caldo Criollita Maggi®\n" +
                        "2 Unidades Lechuga Romana, bien lavada\n" +
                        "1/2 Taza Mayonesa Maggi®\n" +
                        "2 Cucharadas Mostaza Maggi®\n" +
                        "1/4 Taza Vinagre\n" +
                        "2 Cucharadas Queso Parmesano Rallado\n" +
                        "2 Cucharadas Alcaparras\n" +
                        "1 Unidad Anchoa En aceite, escurridas y picadas",
                "30 minutos ",
                categoriasReceta3,
                "Gissela Martinez",
            )
        )


        //Ensalada rusa
        var categoriasReceta4 = ArrayList<String>()
        categoriasReceta4.add("Ensalada")
        categoriasReceta4.add("Guarnicion")


        lista.add(
            Receta(
                R.drawable.receta_portada_ensaladarusa,
                R.drawable.receta_video_ensaladarusa,
                "Ensalada Rusa",
                "9.0",
                "Acompaña esta preparación con tu carne preferida preparada al horno o al vapor.",
                "5 Unidades Papas Peladas y picadas en cubos\n" +
                        "1/4 Taza Arvejas\n" +
                        "1 Cubo Caldo De Gallina Maggi®\n" +
                        "1/4 Taza Choclo\n" +
                        "1/4 Taza Mayonesa Maggi®\n" +
                        "2 Tazas Agua\n" +
                        "1/4 Taza Zanahoria Picada en cubitos y cocida",
                "14 minutos ",
                categoriasReceta4,
                "Luisa Mero",


                )
        )

        //Pie de limón
        var categoriasReceta5 = ArrayList<String>()
        categoriasReceta5.add("Postre")

        lista.add(
            Receta(
                R.drawable.receta_portada_pielimon,
                R.drawable.receta_video_pielimon,
                "Ensalada Rusa",
                "9.0",
                "Delicioso pie de limón sencillo de preparar",
                "1 1/2 Taza Galletas María Nestlé® hechas miga\n" +
                        "1/4 Taza Mantequilla sin Sal Derretir la mantequilla\n" +
                        "1 Lata Leche Condensada LA LECHERA ®\n" +
                        "1/2 Taza Jugo De Limón\n" +
                        "1 cucharadita de cascara de limón\n" +
                        "3 Unidades Yema De Huevo\n" +
                        "3 Unidades Clara De Huevo\n" +
                        "1/4 Taza Azúcar",
                "27 minutos ",
                categoriasReceta5,
                "Enrique Calva",

                )
        )

        return  lista
    }


    private fun generarSlider():ArrayList<Slide>{
        var lista = ArrayList<Slide>()

        lista.add(
            Slide(
                R.drawable.receta_video_secopollo,

            )
        )

        lista.add(
            Slide(
                R.drawable.receta_portada_secopollo,

            )
        )
        return  lista
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}