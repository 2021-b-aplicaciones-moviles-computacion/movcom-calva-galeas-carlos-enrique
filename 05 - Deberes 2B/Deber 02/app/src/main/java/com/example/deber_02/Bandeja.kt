package com.example.deber_02

import android.os.Parcel
import android.os.Parcelable

class Bandeja(
    val usuario: String?,
    val descripcion: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(usuario)
        parcel.writeString(descripcion)
    }

    companion object CREATOR : Parcelable.Creator<Bandeja> {
        override fun createFromParcel(parcel: Parcel): Bandeja {
            return Bandeja(parcel)
        }

        override fun newArray(size: Int): Array<Bandeja?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String{
        return "${usuario}-${descripcion}"
    }

}