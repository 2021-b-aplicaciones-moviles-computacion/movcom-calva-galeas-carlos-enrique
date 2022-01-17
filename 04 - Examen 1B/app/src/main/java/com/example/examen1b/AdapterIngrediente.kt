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

class AdapterIngrediente(
    private val contexto: IngredientesActivity,
    private var listaIngrediente: ArrayList<Ingrediente>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdapterIngrediente.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idRecetaTextView: TextView
        var NombreTextView: TextView
        var caloriasTextView: TextView
        var fechaCompraTextView: TextView
        var InventarioTextView: TextView
        var PrecioUnitarioTextView: TextView
        var linearLayoutId: LinearLayoutCompat


        init {
            idRecetaTextView = view.findViewById(R.id.tvidRecetaIngrediente)
            NombreTextView = view.findViewById(R.id.tvNombreIngrediente)
            caloriasTextView = view.findViewById(R.id.tvCaloriasIngrediente)
            PrecioUnitarioTextView = view.findViewById(R.id.tvPrecioUnitarioIngrediente)
            fechaCompraTextView = view.findViewById(R.id.tvFechaCompraIngrediente)
            InventarioTextView = view.findViewById(R.id.tvInventarioIngrediente)
            linearLayoutId = view.findViewById(R.id.LinearLayoutIdIngrediente)
            linearLayoutId.setOnClickListener { popUpMenus(it) }
        }

        private fun popUpMenus(v: View) {
            BaseDeDatos.TablaIngrediente = SQLiteHelper(contexto)
            var idItem = BaseDeDatos.TablaIngrediente!!.consultaListaIngredientes()[adapterPosition]
            val popup = PopupMenu(contexto, v)
            popup.inflate(R.menu.menu_ingredientes)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    //Editar
                    R.id.menuEditarIngrediente -> {
                        val intentExplicito =
                            Intent(contexto, IngredientesFormularioActualizacion::class.java)
                        intentExplicito.putExtra("id", idItem)
                        contexto.startActivity(intentExplicito)
                        contexto.finish()
                        Toast.makeText(
                            contexto,
                            "Editar ingrediente -> ${adapterPosition}",
                            Toast.LENGTH_SHORT
                        ).show()
                        true
                    }


                    //Eliminar
                    R.id.menuEliminarIngrediente -> {

                        val builder = AlertDialog.Builder(contexto)
                        builder.setTitle("Confirmación!! \n Esta seguro de eliminar este ingrediente?")
                        builder.setPositiveButton(
                            "Aceptar",
                            DialogInterface.OnClickListener { dialog, which ->
                                BaseDeDatos.TablaIngrediente!!.eliminarIngredientePorId(idItem.id)
                                Toast.makeText(
                                    contexto,
                                    "Ingrediente eliminado -> ${adapterPosition}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                contexto.finish()
                                contexto.startActivity(
                                    Intent(
                                        contexto,
                                        IngredientesActivity::class.java
                                    )
                                )
                            }
                        )

                        builder.setNegativeButton(
                            "Cancelar", null
                        )

                        val dialog = builder.create()
                        dialog.show()
                        true
                    }


                    else -> true

                }
            }
            popup.show()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_ingrediente,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingrediente = listaIngrediente[position]
        holder.idRecetaTextView.text = ingrediente.idReceta.toString()
        holder.NombreTextView.text = ingrediente.nombre
        holder.caloriasTextView.text = ingrediente.calorias.toString()
        holder.PrecioUnitarioTextView.text = ingrediente.precioUnitario.toString()
        holder.fechaCompraTextView.text = ingrediente.fechaCompra
        if ( ingrediente.inventario.toString()== "true"){
            holder.InventarioTextView.text = "Si"
        }
        else {
            holder.InventarioTextView.text = "No"
        }

    }

    override fun getItemCount(): Int {
        return listaIngrediente.size
    }
}