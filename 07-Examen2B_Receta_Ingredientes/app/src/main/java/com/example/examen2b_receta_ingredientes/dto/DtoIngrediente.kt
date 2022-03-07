package com.example.examen2b_receta_ingredientes.dto

import android.os.Parcel
import android.os.Parcelable

class DtoIngrediente (
    var nombre: String?= null,
    var calorias: Int?= null,
    var fechaCompra: String?= null,
    var idReceta: String? = null,
    var precioUnitario: Double?= null,
    var inventario: Boolean?= null,
    var uid: String? = null
        ):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte(),
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
        parcel.writeInt(calorias!!)
        parcel.writeString(fechaCompra)
        parcel.writeString(idReceta)
        parcel.writeDouble(precioUnitario!!)
        parcel.writeByte(if (inventario!!) 1 else 0)
        parcel.writeString(uid)
    }

    companion object CREATOR : Parcelable.Creator<DtoIngrediente> {
        override fun createFromParcel(parcel: Parcel): DtoIngrediente {
            return DtoIngrediente(parcel)
        }

        override fun newArray(size: Int): Array<DtoIngrediente?> {
            return arrayOfNulls(size)
        }
    }

}