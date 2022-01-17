package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class Receta (
    var id: Int,
    var nombre: String?,
    var tipoReceta: String?,
    var numeroIngredientes: Int?,
    var precio: Double?,
    var tiempoPreparacion: Int?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        (parcel.readValue(Int::class.java.classLoader) as? Int)!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        (parcel.readValue(Int::class.java.classLoader) as? Int),
    ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(tipoReceta)
        parcel.writeValue(numeroIngredientes)
        parcel.writeValue(precio)
        parcel.writeValue(tiempoPreparacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Receta> {
        override fun createFromParcel(parcel: Parcel): Receta {
            return Receta(parcel)
        }

        override fun newArray(size: Int): Array<Receta?> {
            return arrayOfNulls(size)
        }
    }


}