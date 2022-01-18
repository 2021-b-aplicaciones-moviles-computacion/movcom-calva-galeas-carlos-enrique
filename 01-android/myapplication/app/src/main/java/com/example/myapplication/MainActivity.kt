package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val data = result.data
                Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                Log.i("intent-epn", "${data?.getIntExtra("edadModificado", 0)}")
            }
        }
    }

    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BASE DE DATOS SQLITE

        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)

        if (EBaseDeDatos.TablaUsuario != null) {
            val idQuemado = 2
            EBaseDeDatos.TablaUsuario?.crearUsuarioFormulario(
                "Adrian",
                "Adrian desc"
            )
            var consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.actualizarUsuarioFormulario(
                "Vicente",
                "Vicenet desc",
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.eliminarUsuarioFormulario(
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
        }


        val botonCicloVida = findViewById<Button>(R.id.btn_ir_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }
        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)
            }

        val botonRecyclerView = findViewById<Button>(R.id.btn_ir_recycler_view)
        botonRecyclerView
            .setOnClickListener {
                abrirActividadConParametros(GRecyclerView::class.java)
            }
    }

    fun abrirActividadConParametros(
        clase: Class<*>,
    ) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Adrian")
        intentExplicito.putExtra("apellido", "Eguez")
        intentExplicito.putExtra("edad", 32)
        intentExplicito.putExtra("entrenador", BEntrenador("a", "b"))
//        resultLauncher.launch(intentExplicito)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)// 401
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> { // 401
                if (resultCode == RESULT_OK) {
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
                if (resultCode == RESULT_CANCELED) {
                    Log.i("intent-epn", "Cancelado")
                }
            }
            CODIGO_RESPUESTA_INTENT_IMPLICITO -> {
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        if (data.data != null) {
                            val uri: Uri = data.data!!
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(
                                indiceTelefono!!
                            )
                            cursor?.close()
                            Log.i("intent-epn", "Telefono ${telefono}")
                        }
                    }
                }
            }
        }
    }

    fun irActividad(
        clase: Class<*>,
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}