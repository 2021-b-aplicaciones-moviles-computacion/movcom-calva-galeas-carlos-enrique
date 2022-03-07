package com.example.proyecto2b.ui.clases

import java.io.Serializable

class Receta (val ImagePortada:Int,
              val ImageVideoReceta:Int,
              val Nombre: String?,
              val Calificacion:String?,
              val Descripcion:String?,
              val ingredientes:String?,
              val tiempoPrearacion:String?,
              val Categorias: ArrayList<String>,
              val Autor: String?

): Serializable {

}