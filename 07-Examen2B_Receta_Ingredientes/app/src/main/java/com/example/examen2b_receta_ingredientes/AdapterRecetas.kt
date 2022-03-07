package com.example.examen2b_receta_ingredientes

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_receta_ingredientes.dto.DtoReceta
import com.example.examen2b_receta_ingredientes.dto.DtoIngrediente
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdapterRecetas(
    private val contexto: RecetasActivity,
    private var listaReceta: ArrayList<DtoReceta>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdapterRecetas.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var tipoRecetaTextView: TextView
        var numeroIngredientesTextView: TextView
        var precioTextView: TextView
        var tiempoPreparacionTextView: TextView
        var linearLayoutId: LinearLayoutCompat


        init {
            nombreTextView = view.findViewById(R.id.tvNombreReceta)
            tipoRecetaTextView = view.findViewById(R.id.tvTipoReceta)
            numeroIngredientesTextView = view.findViewById(R.id.tvNroIngredientes)
            precioTextView = view.findViewById(R.id.tvPrecio)
            tiempoPreparacionTextView = view.findViewById(R.id.tvTiempoPreparacion)
            linearLayoutId = view.findViewById(R.id.LinearLayoutIdMaterias)
            linearLayoutId.setOnClickListener { popUpMenus(it) }
        }

        private fun popUpMenus(v: View) {
            var registroReceta: (MutableList<DocumentSnapshot>)

            val listaReceta = ArrayList<DtoReceta>()

            val db = Firebase.firestore
            val referenciaReceta = db.collection("receta")
            referenciaReceta
                .get()
                .addOnSuccessListener {
                    registroReceta = it.documents
                    registroReceta.forEach { iteracion ->
                        val objReceta = iteracion.toObject(DtoReceta::class.java)
                        objReceta!!.uid = iteracion.id
                        objReceta.nombre = iteracion.get("nombre").toString()
                        objReceta.tipoReceta = iteracion.get("tipoReceta").toString()
                        objReceta.numeroIngredientes = iteracion.get("numeroIngredientes").toString().toInt()
                        objReceta.precio = iteracion.get("precio").toString().toDouble()
                        objReceta.tiempoPreparacion = iteracion.get("tiempoPreparacion").toString().toInt()

                        listaReceta.add(objReceta)
                    }
                    val idItem = listaReceta[adapterPosition]
                    val popup = PopupMenu(contexto, v)
                    popup.inflate(R.menu.menu_recetas)
                    popup.setOnMenuItemClickListener {
                        when (it.itemId) {

                            //Editar
                            R.id.menuEditarRecetas -> {
                                val intentExplicito =
                                    Intent(contexto, RecetasFormularioActualizacion::class.java)
                                intentExplicito.putExtra("id", idItem)
                                contexto.startActivity(intentExplicito)
                                contexto.finish()
                                Toast.makeText(contexto, "Editar clicked", Toast.LENGTH_SHORT)
                                    .show()
                                true
                            }

                            //Eliminar
                            R.id.menuEliminarRecetas -> {
                                val builder = AlertDialog.Builder(contexto)
                                builder.setTitle("Esta seguro de eliminar esta receta?")
                                builder.setPositiveButton(
                                    "Aceptar",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        referenciaReceta.document(idItem.uid.toString())
                                            .delete()
                                            .addOnSuccessListener {
                                                Toast.makeText(
                                                    contexto,
                                                    "Eliminar clicked -- ${adapterPosition}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                contexto.finish()
                                                contexto.startActivity(
                                                    Intent(
                                                        contexto,
                                                        RecetasActivity::class.java
                                                    )
                                                )
                                            }
                                    }
                                )

                                builder.setNegativeButton(
                                    "Cancelar", null
                                )

                                val dialog = builder.create()
                                dialog.show()

                                true
                            }

                            //Lista Ingredientes

                            R.id.menuListaIngredientes -> {

                                var registroingredientes: (MutableList<DocumentSnapshot>)
                                val listaIngredientes = ArrayList<DtoIngrediente>()
                                val db = Firebase.firestore

                                val referenciaIngredientes = db.collection("ingrediente")

                                referenciaIngredientes.whereEqualTo("idReceta", idItem.uid)
                                    .get()
                                    .addOnSuccessListener {
                                        registroingredientes = it.documents
                                        registroingredientes.forEach { iteracion ->
                                            val objIngrediente =
                                                iteracion.toObject(DtoIngrediente::class.java)
                                            objIngrediente!!.uid = iteracion.id
                                            objIngrediente.calorias = iteracion.get("calorias").toString().toInt()
                                            objIngrediente.precioUnitario = iteracion.get("precioUnitario").toString().toDouble()
                                            objIngrediente.inventario = iteracion.get("inventario").toString().toBoolean()
                                            objIngrediente.fechaCompra = iteracion.get("fechaCompra").toString()
                                            objIngrediente.idReceta= iteracion.get("idReceta").toString()
                                            objIngrediente.nombre =iteracion.get("nombre").toString()

                                            listaIngredientes.add(objIngrediente)
                                        }
                                        //Iniciar Recycler View
                                        iniciarRecyclerView(
                                            listaIngredientes,
                                            IngredientesActivity(),
                                            recyclerView
                                        )
                                    }

                                true
                            }

                            else -> true
                        }
                    }

                    popup.show()
                }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_recetas,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val receta = listaReceta[position]
        holder.nombreTextView.text = receta.nombre
        holder.tipoRecetaTextView.text = receta.tipoReceta
        holder.numeroIngredientesTextView.text = receta.numeroIngredientes.toString()
        holder.precioTextView.text = receta.precio.toString()
        holder.tiempoPreparacionTextView.text = receta.tiempoPreparacion.toString()
    }

    override fun getItemCount(): Int {
        return listaReceta.size
    }

    fun iniciarRecyclerView(
        lista: ArrayList<DtoIngrediente>,
        activity: IngredientesActivity,
        recyclerView: RecyclerView
    ) {
        val adaptador = AdapterIngredientes(
            activity,
            // this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

    }

}