package com.example.restcountriesapi

import androidx.databinding.BaseObservable

class CountryData: BaseObservable() {
    private var name: String? = null
    private var topLevelDomain: ArrayList<String>? = null
    private var alpha3Code: String? = null
    private var capital: String? = null
    private var subregion: String? = null
    private var region: String? = null
    private var population: Long = 0
    private var borders: ArrayList<String>? = null
    private var nativeName: String? = null
    private var currencies: ArrayList<Currency>? = null
    private var languages: ArrayList<Language>? = null
    private var flag: Flag? = null

    fun receiveAndSetValues(response: CountriesResponse): CountryData {
        name = response.name
        topLevelDomain = response.topLevelDomain
        alpha3Code = response.alpha3Code
        capital = response.capital
        subregion = response.subregion
        region = response.region
        population = response.population
        borders = response.borders
        nativeName = response.nativeName
        currencies = response.currencies
        languages = response.languages
        flag = response.flag

        return this
    }

    fun getName(): String? { return name }
    fun getTopLevelDomain(): ArrayList<String>? { return topLevelDomain }
    fun getAlpha3Code(): String? { return alpha3Code }
    fun getCapital(): String? { return capital }
    fun getSubregion(): String? { return subregion }
    fun getRegion(): String? { return region }
    fun getPopulation(): String { return population.toString() }
    fun getBorders(): ArrayList<String>? { return borders }
    fun getNativeName(): String? { return nativeName }
    fun getCurrencies(): ArrayList<Currency>? { return currencies }
    fun getLanguages(): ArrayList<Language>? { return languages }
    fun getFlag(): Flag? { return flag }
}