package com.example.deber_02

import android.os.Parcel
import android.os.Parcelable

class Videos(
    val enlace: String?,
    val usuario: String?,
    val hashtag: String?,
    val musica: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {

    }

    override fun toString(): String{
        return "${enlace}-${usuario}-${hashtag}-${musica}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
     parcel.writeString(enlace)
        parcel.writeString(usuario)
        parcel.writeString(hashtag)
        parcel.writeString(musica)
    }

    companion object CREATOR : Parcelable.Creator<Videos> {
        override fun createFromParcel(parcel: Parcel): Videos {
            return Videos(parcel)
        }

        override fun newArray(size: Int): Array<Videos?> {
            return arrayOfNulls(size)
        }
    }
}