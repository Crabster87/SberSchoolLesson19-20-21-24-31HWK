package crabster.rudakov.sberschoollesson19hwk.ui.list.adapter

/**
 * Интерфейс, описывающий действия при клике пользователем
 * по каждому значению из списка стран на 1-ом экране
 * */
interface IListItemListener {

    /**
     * Метод описывает действия после клика по значению списка
     *
     * @param url URL страны
     * */
    fun onMessageClick(url: String)

}