package com.example.proyecto2b.ui.clases

import java.io.Serializable

class RecetaFirebase (val uidReceta:String?,
                      var IdYouTube:String?,
                      val descripcion: String?,
                      val calificacion:Double?,
                      var categorias: String?,
                      val tiempoPreparacion: Int?,
                      val nombre:String?,
                      val imagenReceta:String?,
                      var imagenVideoReceta:String?,
                      var autor:String?,
                      var ingredientes:String?

): Serializable {

    override fun toString(): String {
        return "RecetaFireBase(uid=$uidReceta, IdYouTube=$IdYouTube, descripcion=$descripcion, calificacion=$calificacion, categorias=$categorias , tiempoPreparacion=$tiempoPreparacion, nombre=$nombre, imagenReceta=$imagenReceta, portadaReceta=$imagenVideoReceta, autor=$autor, ingredientes=$ingredientes)"
    }
}