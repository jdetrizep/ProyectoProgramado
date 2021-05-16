package com.proyecto.pokedex.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") var name: String,
    @SerializedName("url") var pokemonUrl: String? = "",
    var imageURL: String? = "",
    var description: String? = "",
    var imageShinyURL: String? = "",
    var numeroPokemon: Int? = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(pokemonUrl)
        parcel.writeString(imageURL)
        parcel.writeString(description)
        parcel.writeValue(imageShinyURL)
        parcel.writeValue(numeroPokemon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }

    fun getNumero(): Int{
        val urlPartes = pokemonUrl?.split("/")
        return Integer.parseInt(urlPartes?.get(urlPartes.size - 2))
    }
}