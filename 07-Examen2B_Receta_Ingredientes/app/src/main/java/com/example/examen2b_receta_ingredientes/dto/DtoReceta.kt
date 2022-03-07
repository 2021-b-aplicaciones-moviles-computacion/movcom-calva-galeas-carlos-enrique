package com.example.examen2b_receta_ingredientes.dto

import android.os.Parcel
import android.os.Parcelable

class DtoReceta(
    var nombre: String?= null,
    var tipoReceta: String?= null,
    var numeroIngredientes: Int?= null,
    var precio: Double?= null,
    var tiempoPreparacion:Int?= null,
    var uid: String? = null
        ):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }


    override fun toString(): String {
        return super.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(tipoReceta)
        parcel.writeValue(numeroIngredientes)
        parcel.writeValue(precio)
        parcel.writeValue(tiempoPreparacion)
        parcel.writeString(uid)
    }

    companion object CREATOR : Parcelable.Creator<DtoReceta> {
        override fun createFromParcel(parcel: Parcel): DtoReceta {
            return DtoReceta(parcel)
        }

        override fun newArray(size: Int): Array<DtoReceta?> {
            return arrayOfNulls(size)
        }
    }
}