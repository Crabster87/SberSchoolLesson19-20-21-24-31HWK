package crabster.rudakov.sberschoollesson19hwk.data.model

/**
 * Класс, хранящий полученные по запросу данные по стране
 *
 * @param name ключ пары со значением названия страны из JSON-файла
 * @param url ключ пары со значениями URL страны
 * */
data class CountryItem(
    val name: String,
    val url: String
)