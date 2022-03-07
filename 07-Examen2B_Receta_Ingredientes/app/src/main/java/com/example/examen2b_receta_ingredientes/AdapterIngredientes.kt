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
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_receta_ingredientes.dto.DtoIngrediente
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdapterIngredientes(
    private val contexto: IngredientesActivity,
    private var listaIngrediente: ArrayList<DtoIngrediente>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdapterIngredientes.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idRecetaTextView: TextView
        var caloriasTextView: TextView
        var precioUnitarioTextView: TextView
        var nombreTextView: TextView
        var fechaCompraTextView: TextView
        var inventarioTextView: TextView
        var linearLayoutId: LinearLayoutCompat

        init {
            idRecetaTextView = view.findViewById(R.id.tvidIngredienteReceta)
            caloriasTextView = view.findViewById(R.id.tvCaloriasIngrediente)
            precioUnitarioTextView = view.findViewById(R.id.tvPrecioUnitarioIngrediente)
            nombreTextView = view.findViewById(R.id.tvNombreIngrediente)
            fechaCompraTextView = view.findViewById(R.id.tvFechaCompraIngrediente)
            inventarioTextView = view.findViewById(R.id.tvInventarioIngrediente)
            linearLayoutId = view.findViewById(R.id.LinearLayoutIdIngrediente)
            linearLayoutId.setOnClickListener { popUpMenus(it) }
        }

        private fun popUpMenus(v: View) {
            var registroIngredientes: (MutableList<DocumentSnapshot>)
            val listaIngredientes = ArrayList<DtoIngrediente>()
            val db = Firebase.firestore

            val referenciaIngredientes = db.collection("ingrediente")

            referenciaIngredientes
                .get()
                .addOnSuccessListener {
                    registroIngredientes = it.documents
                    registroIngredientes.forEach { iteracion ->
                        val objIngrediente = iteracion.toObject(DtoIngrediente::class.java)
                        objIngrediente!!.uid = iteracion.id
                        objIngrediente.nombre = iteracion.get("nombre").toString()
                        objIngrediente.calorias = iteracion.get("calorias").toString().toInt()
                        objIngrediente.fechaCompra = iteracion.get("fechaCompra").toString()
                        objIngrediente.precioUnitario = iteracion.get("precioUnitario").toString().toDouble()
                        objIngrediente.idReceta = iteracion.get("idReceta").toString()
                        objIngrediente.inventario =iteracion.get("inventario").toString().toBoolean()


                        listaIngredientes.add(objIngrediente)
                    }

                    val idItem =listaIngredientes[adapterPosition]
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
                                builder.setTitle("Esta seguro de eliminar este ingrediente?")
                                builder.setPositiveButton(
                                    "Aceptar",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        referenciaIngredientes.document(idItem.uid.toString())
                                            .delete()
                                            .addOnSuccessListener {
                                                Toast.makeText(
                                                    contexto,
                                                    "Ingediente eliminado -> ${adapterPosition}",
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
        holder.idRecetaTextView.text = ingrediente.idReceta
        holder.caloriasTextView.text = ingrediente.calorias.toString()
        holder.precioUnitarioTextView.text = ingrediente.precioUnitario.toString()
        holder.nombreTextView.text = ingrediente.nombre
        holder.fechaCompraTextView.text = ingrediente.fechaCompra
        holder.inventarioTextView.text = ingrediente.inventario.toString()
    }

    override fun getItemCount(): Int {
        return listaIngrediente.size
    }

}