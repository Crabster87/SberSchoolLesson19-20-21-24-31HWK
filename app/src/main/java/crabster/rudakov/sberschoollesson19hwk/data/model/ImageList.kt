package crabster.rudakov.sberschoollesson19hwk.data.model

/**
 * Класс, хранящий список всех URL изображений по стране
 *
 * @param hits список URL
 * */
data class ImageList (
    val hits: List<Hits>?
)

/**
 * Класс, хранящий URL изображения по стране
 *
 * @param webformatURL URL картинки
 * */
data class Hits(
    val webformatURL: String
)