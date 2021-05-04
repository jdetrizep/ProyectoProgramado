package com.proyecto.pokedex.models

import android.os.Parcel
import android.os.Parcelable

data class Entrenador (val name: String, val generoMasculino: Boolean, val generoFemenino: Boolean) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (generoMasculino) 1 else 0)
        parcel.writeByte(if (generoFemenino) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Entrenador> {
        override fun createFromParcel(parcel: Parcel): Entrenador {
            return Entrenador(parcel)
        }

        override fun newArray(size: Int): Array<Entrenador?> {
            return arrayOfNulls(size)
        }
    }
}