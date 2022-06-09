package com.example.restcountriesapi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CountriesResponse: Serializable {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("topLevelDomain")
    var topLevelDomain: ArrayList<String>? = null

    @SerializedName("alpha3Code")
    var alpha3Code: String? = null

    @SerializedName("capital")
    var capital: String? = null

    @SerializedName("subregion")
    var subregion: String? = null

    @SerializedName("region")
    var region: String? = null

    @SerializedName("population")
    var population: Long = 0

    @SerializedName("borders")
    var borders: ArrayList<String>? = null

    @SerializedName("nativeName")
    val nativeName: String? = null

    @SerializedName("currencies")
    var currencies: ArrayList<Currency>? = null

    @SerializedName("languages")
    var languages: ArrayList<Language>? = null

    @SerializedName("flags")
    var flag: Flag? = null
}

class Currency {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("symbol")
    var symbol: String? = null
}

class Language {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("nativeName")
    var nativeName: String? = null
}

class Flag {
    @SerializedName("png")
    var png: String? = null
}
