package com.example.examen1b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(
    contexto: Context?
) : SQLiteOpenHelper(
    contexto,
    "Examen 01",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaReceta = """ 
            create table RECETA (
               IDRECETA            integer primary key autoincrement,
               NOMBRERECETA               varchar(50)           not null UNIQUE,
               TIPORECETA           varchar(50)          not null,
               NUMEROINGREDIENTES   int                  not null,
               PRECIO               double          not null,
               TIEMPOPREPARACION    int                  not null               
            )           
        """.trimIndent()
        Log.i("bdd", "Creando la tabla de receta")
        db?.execSQL(scriptCrearTablaReceta)

        val scriptCrearTablaIngrediente = """ 
            create table INGREDIENTE (
               IDINGREDIENTE        integer primary key autoincrement,
               IDRECETA             int,
               NOMBRE               varchar(50)        not null UNIQUE,
               CALORIAS             int                not null,
               FECHACOMPRA          date               not null,
               INVENTARIO           bit                not null,
               PRECIOUNITARIO       double                not null,
               CONSTRAINT FK_INGREDIENTE_RELATIONS_RECETA
                FOREIGN KEY (IDRECETA)
                REFERENCES RECETA (IDRECETA)
            )        
        """.trimIndent()
        Log.i("bdd", "Creando la tabla de ingrediente")
        db?.execSQL(scriptCrearTablaIngrediente)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }



    fun crearRecetaFormulario(
        nombre: String,
        tipoReceta: String,
        numeroIngredientes: Int,
        precio: Double,
        tiempoPreparacion: Int
    ): Boolean {
        val conexionEscrituta = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("NOMBRERECETA", nombre)
        valoresAGuardar.put("TIPORECETA ", tipoReceta)
        valoresAGuardar.put("NUMEROINGREDIENTES  ", numeroIngredientes)
        valoresAGuardar.put("PRECIO", precio)
        valoresAGuardar.put("TIEMPOPREPARACION", tiempoPreparacion)
        val resultadoEscritura: Long = conexionEscrituta
            .insert(
                "RECETA",
                null,
                valoresAGuardar
            )
        conexionEscrituta.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }


    fun consultaRecetaTotal(): ArrayList<Receta> {
        val scriptConsultarRecetaPorNombre = "SELECT * FROM RECETA"
        val baseDatosLectura = readableDatabase
        val listaReceta = arrayListOf<Receta>()

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarRecetaPorNombre,
            null
        )

        val existeReceta = resultadoConsultaLectura.moveToFirst()
        if (existeReceta) {
            do {
                val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val tipoReceta = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val numeroIngredientes = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val precio = resultadoConsultaLectura.getDouble(4) //Columna indice 4 -> AULA
                val tiempoPreparacion = (resultadoConsultaLectura.getInt(5))  //C}}

                if (nombre != null) {
                    listaReceta.add(Receta(id,nombre, tipoReceta, numeroIngredientes, precio, tiempoPreparacion))
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resultadoConsultaLectura.toString())
        return listaReceta
    }


    fun consultarRecetaPorId(id: Int): Receta {

        val scriptConsultarRecetaPorId =
            "SELECT * FROM RECETA WHERE IDRECETA = '${id}'"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarRecetaPorId,
            null
        )
        val existeReceta = resultadoConsultaLectura.moveToFirst()
        val RecetaEncontrada = Receta(0, "", "", 0, 0.0, 0)
        if (existeReceta) {
            do {
                val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val tipoReceta = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val numeroIngredientes = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val precio = resultadoConsultaLectura.getDouble(4) //Columna indice 4 -> AULA
                val tiempoPreparacion = (resultadoConsultaLectura.getInt(5))  //C


                if ( nombre != null) {
                    RecetaEncontrada.id = id
                    RecetaEncontrada.nombre = nombre
                    RecetaEncontrada.tipoReceta = tipoReceta
                    RecetaEncontrada.numeroIngredientes = numeroIngredientes
                    RecetaEncontrada.precio = precio
                    RecetaEncontrada.tiempoPreparacion = tiempoPreparacion
                    //arregloUsuario.add(usuarioEncontrado)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return RecetaEncontrada
    }


    fun consultarRecetaPorNombre(nombre: String): Receta {
        val scriptConsultarRecetaPorNombre =
            "SELECT * FROM RECETA WHERE NOMBRERECETA = '${nombre}'"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarRecetaPorNombre,
            null
        )
        val existeReceta = resultadoConsultaLectura.moveToFirst()
        //val arregloUsuario = arrayListOf<EUsuarioBDD>()       //En caso de3 necesitar un arreglo de registros
        val RecetaEncontrada = Receta(0, "", "", 0, 0.0, 0)
        if (existeReceta) {
            do {
                val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val tipoReceta = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val numeroIngredientes = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val precio = resultadoConsultaLectura.getDouble(4) //Columna indice 4 -> AULA
                val tiempoPreparacion = (resultadoConsultaLectura.getInt(5))  //C

                if (nombre != null) {
                    RecetaEncontrada.id = id
                    RecetaEncontrada.nombre = nombre
                    RecetaEncontrada.tipoReceta = tipoReceta
                    RecetaEncontrada.numeroIngredientes = numeroIngredientes
                    RecetaEncontrada.precio = precio
                    RecetaEncontrada.tiempoPreparacion = tiempoPreparacion

                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return RecetaEncontrada
    }


    fun eliminarRecetaPorNombre(nombre: String): Boolean {

        //val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        var resultadoEliminacion = conexionEscritura
            .delete(
                "RECETA",
                "NOMBRERECETA=?",
                arrayOf(nombre)
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() != -1) {
            Log.i("bdd", "Receta ELiminada -> ${nombre}")
            true
        } else {
            Log.i("bdd", "No se pudo eliminar ")
            false
        }
    }

    fun eliminarRecetaPorId(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "RECETA",
                "IDRECETA=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() != -1) {
            Log.i("bdd", "Receta ELiminada -> ${id}")
            true
        } else {
            Log.i("bdd", "No se pudo eliminar ")
            false
        }

    }

    fun actualizarRecetaFormulario(
        idActualizar: Int,
        nombre: String,
        tipoReceta: String,
        numeroIngredientes: Int,
        precio: Double,
        tiempoPreparacion: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("NOMBRERECETA", nombre)
        valoresAActualizar.put("TIPORECETA", tipoReceta)
        valoresAActualizar.put("NUMEROINGREDIENTES", numeroIngredientes)
        valoresAActualizar.put("PRECIO", precio)
        valoresAActualizar.put("TIEMPOPREPARACION", tiempoPreparacion)
        val resultadoActualización = conexionEscritura
            .update(
                "RECETA",
                valoresAActualizar,
                "IDRECETA=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualización.toInt() == -1) false else true
    }


//    Métodos para Ingrediente

    fun crearIngredienteFormulario(
        idReceta: Int,
        nombre: String,
        calorias: Int,
        fechaCompra: String,
        inventario: Boolean,
        precioUnitario: Double
    ): Boolean {
        val conexionEscrituta = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("IDRECETA", idReceta)
        valoresAGuardar.put("NOMBRE", nombre)
        valoresAGuardar.put("CALORIAS", calorias)
        valoresAGuardar.put("FECHACOMPRA", fechaCompra)
        valoresAGuardar.put("INVENTARIO", inventario)
        valoresAGuardar.put("PRECIOUNITARIO", precioUnitario)
        val resultadoEscritura: Long = conexionEscrituta
            .insert(
                "INGREDIENTE",
                null,
                valoresAGuardar
            )
        conexionEscrituta.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultaListaIngredientes(): ArrayList<Ingrediente> {
        val scriptConsultarIngrediente = "SELECT * FROM INGREDIENTE"
        val baseDatosLectura = readableDatabase
        val listaIngrediente = arrayListOf<Ingrediente>()
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarIngrediente,
            null
        )

        val existeIngrediente = resultadoConsultaLectura.moveToFirst()


        if (existeIngrediente) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val idReceta = resultadoConsultaLectura.getInt(1)
                val nombre = resultadoConsultaLectura.getString(2)
                val calorias = resultadoConsultaLectura.getInt(3)
                val fechaCompra = resultadoConsultaLectura.getString(4)
                val inventario = (resultadoConsultaLectura.getInt(5)) > 0
                val precioUnitario = resultadoConsultaLectura.getDouble(6)

                if (id != null) {
                    listaIngrediente.add(
                        Ingrediente(
                            id,
                            idReceta,
                            nombre,
                            calorias,
                            fechaCompra,
                            inventario,
                            precioUnitario
                        )
                    )
                }

            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resultadoConsultaLectura.toString())
        return listaIngrediente
    }


    fun consultarIngredientePorId(id: Int):  ArrayList<Ingrediente> {

        val scriptConsultarIngredientePorId =
            "SELECT * FROM INGREDIENTE WHERE IDRECETA = '${id}'"
        val baseDatosLectura = readableDatabase
        val listaIngrediente = arrayListOf<Ingrediente>()
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarIngredientePorId,
            null
        )
        val existeIngrediente = resultadoConsultaLectura.moveToFirst()

        if (existeIngrediente) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val idReceta = resultadoConsultaLectura.getInt(1)
                val nombre = resultadoConsultaLectura.getString(2)
                val calorias = resultadoConsultaLectura.getInt(3)
                val fechaCompra = resultadoConsultaLectura.getString(4)
                val inventario = (resultadoConsultaLectura.getInt(5)) > 0
                val precioUnitario = resultadoConsultaLectura.getDouble(6)


                if (id != null) {
                    listaIngrediente.add(
                            Ingrediente(
                                id,
                                idReceta,
                                nombre,
                                calorias,
                                fechaCompra,
                                inventario,
                                precioUnitario
                        )
                    )
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resultadoConsultaLectura.toString())
        return listaIngrediente
    }

    fun eliminarIngredientePorId(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "INGREDIENTE",
                "IDINGREDIENTE=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() != -1) {
            Log.i("bdd", "Ingrediente ELiminado -> ${id}")
            true
        } else {
            Log.i("bdd", "No se puedo eliminar ")
            false
        }

    }


    fun actualizarIngredienteFormulario(
        idActualizar: Int,
        idReceta: Int,
        nombre: String,
        calorias: Int,
        fechaCompra: String,
        inventario: Boolean,
        precioUnitario: Double
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("IDRECETA", idReceta)
        valoresAActualizar.put("NOMBRE", nombre)
        valoresAActualizar.put("CALORIAS", calorias)
        valoresAActualizar.put("FECHACOMPRA", fechaCompra)
        valoresAActualizar.put("INVENTARIO", inventario)
        valoresAActualizar.put("PRECIOUNITARIO", precioUnitario)
        val resultadoActualización = conexionEscritura
            .update(
                "INGREDIENTE",
                valoresAActualizar,
                "IDINGREDIENTE=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualización.toInt() == -1) false else true
    }

}