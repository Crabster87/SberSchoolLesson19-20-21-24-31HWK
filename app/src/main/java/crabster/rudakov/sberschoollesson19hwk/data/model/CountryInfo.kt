package crabster.rudakov.sberschoollesson19hwk.data.model

import com.google.gson.annotations.SerializedName

/**
 * Класс, хранящий необходимые для отображения на 2-ом экране данные
 * по стране
 *
 * @param names ключ пары со значением названия страны из JSON-файла
 * @param maps ключ пары со значениями координат страны из JSON-файла
 * @param currency ключ пары со значением валюты страны из JSON-файла
 * @param languageList ключ пары со значением списка языков страны из JSON-файла
 * @param neighborsList ключ пары со значением списка соседних стран из JSON-файла
 * */
data class CountryInfo(
    val names: Names,
    val maps: Maps,
    val currency: Currency,
    @SerializedName("language")
    val languageList: List<Language>?,
    @SerializedName("neighbors")
    val neighborsList: List<Neighbors>?
)

/**
 * Класс, хранящий название страны и её флаг
 *
 * @param name ключ пары со значением названия из JSON-файла
 * @param full ключ пары со значениями полного названия из JSON-файла
 * @param iso2 ключ пары со значениями аббревиатуры флага из JSON-файла
 * */
data class Names(
    val name: String,
    val full: String,
    val iso2: String
)

/**
 * Класс, хранящий координаты страны
 *
 * @param lat ключ пары со значением широты из JSON-файла
 * @param long ключ пары со значениями долготы из JSON-файла
 * */
data class Maps(
    val lat: Float,
    val long: Float
)

/**
 * Класс, хранящий валюту страны
 *
 * @param name ключ пары со значением названия валюты из JSON-файла
 * */
data class Currency(
    val name: String
)

/**
 * Класс, хранящий государственный язык страны
 *
 * @param language ключ пары со значением названия языка из JSON-файла
 * */
data class Language(
    val language: String
)

/**
 * Класс, хранящий названия соседей страны
 *
 * @param name ключ пары со значением названия соседней страны из JSON-файла
 * */
data class Neighbors(
    val name: String
)