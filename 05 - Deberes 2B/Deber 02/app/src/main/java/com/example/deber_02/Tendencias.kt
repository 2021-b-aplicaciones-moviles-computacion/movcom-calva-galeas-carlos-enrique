package com.example.deber_02

import android.os.Parcel
import android.os.Parcelable

class Tendencias(
    val titulo: String?,
    val hash: String?,
    val numero: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(hash)
        parcel.writeString(numero)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tendencias> {
        override fun createFromParcel(parcel: Parcel): Tendencias {
            return Tendencias(parcel)
        }

        override fun newArray(size: Int): Array<Tendencias?> {
            return arrayOfNulls(size)
        }
    }
    override fun toString(): String{
        return "${titulo}-${hash}-${numero}"
    }

}