package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class Ingrediente(
    val id: Int,
    val idReceta: Int,
    var nombre: String?,
    var calorias: Int,
    var fechaCompra: String?,
    var inventario: Boolean,
    var precioUnitario: Double,

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(idReceta)
        parcel.writeString(nombre)
        parcel.writeInt(calorias)
        parcel.writeString(fechaCompra)
        parcel.writeByte(if (inventario) 1 else 0)
        parcel.writeDouble(precioUnitario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingrediente> {
        override fun createFromParcel(parcel: Parcel): Ingrediente {
            return Ingrediente(parcel)
        }

        override fun newArray(size: Int): Array<Ingrediente?> {
            return arrayOfNulls(size)
        }
    }

}