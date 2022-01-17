package com.example.examen1b

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
import androidx.recyclerview.widget.RecyclerView

class AdapterReceta(
    private val contexto: RecetasActivity,
    private var listaReceta: ArrayList<Receta>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdapterReceta.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idTextView: TextView
        var nombreTextView: TextView
        var tipoRecetaTextView: TextView
        var numeroIngredientesTextView: TextView
        var precioTextView: TextView
        var tiempoPreparacion: TextView
        var linearLayoutId: LinearLayoutCompat


        init {
            idTextView= view.findViewById(R.id.tvIDReceta)
            nombreTextView = view.findViewById(R.id.tvNombreReceta)
            tipoRecetaTextView = view.findViewById(R.id.tvTipoReceta)
            numeroIngredientesTextView = view.findViewById(R.id.tvNumeroIngredientesReceta)
            precioTextView = view.findViewById(R.id.tvPrecioReceta)
            tiempoPreparacion = view.findViewById(R.id.tvTiempoPreparacionReceta)
            linearLayoutId = view.findViewById(R.id.LinearLayoutIdRecetas)
            linearLayoutId.setOnClickListener { popUpMenus(it) }

        }

        private fun popUpMenus(v: View) {

            BaseDeDatos.TablaReceta = SQLiteHelper(contexto)
            var idItem = BaseDeDatos.TablaReceta!!.consultaRecetaTotal()[adapterPosition]
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

                        Toast.makeText(contexto, "Abriendo Editar", Toast.LENGTH_SHORT).show()
                        true
                    }

                    //Eliminar
                    R.id.menuEliminarRecetas -> {
                        val builder = AlertDialog.Builder(contexto)
                        builder.setTitle("Confirmación!! \n Esta seguro de eliminar esta receta?")
                        builder.setPositiveButton(
                            "Aceptar",
                            DialogInterface.OnClickListener { dialog, which ->
                                BaseDeDatos.TablaReceta!!.eliminarRecetaPorId(idItem.id)
                                Toast.makeText(
                                    contexto,
                                    "Receta eliminada -- ${adapterPosition}",
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
                        )

                        builder.setNegativeButton(
                            "Cancelar",null
                        )

                        val dialog = builder.create()
                        dialog.show()



                        true
                    }

                    //Lista Ingredientes
                    R.id.menuListaIngredientes -> {
                        var listaIngrediente = arrayListOf<Ingrediente>()
                        BaseDeDatos.TablaIngrediente = SQLiteHelper(contexto)
                        if (BaseDeDatos.TablaIngrediente != null) {
                            listaIngrediente =
                                BaseDeDatos.TablaIngrediente!!.consultarIngredientePorId(idItem.id)
                        }

                        iniciarRecyclerView(listaIngrediente, IngredientesActivity(), recyclerView)
                        true
                    }

                    else -> true
                }
            }

            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_recetas,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val receta = listaReceta[position]
        holder.idTextView.text= receta.id.toString()
        holder.nombreTextView.text = receta.nombre
        holder.tipoRecetaTextView.text = receta.tipoReceta
        holder.numeroIngredientesTextView.text = receta.numeroIngredientes.toString()
        holder.precioTextView.text = receta.precio.toString()
        holder.tiempoPreparacion.text = receta.tiempoPreparacion.toString()


    }

    override fun getItemCount(): Int {
        return listaReceta.size
    }

    fun iniciarRecyclerView(
        lista: ArrayList<Ingrediente>,
        activity: IngredientesActivity,
        recyclerView: RecyclerView
    ) {
        val adaptador = AdapterIngrediente(
            activity,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

    }


}