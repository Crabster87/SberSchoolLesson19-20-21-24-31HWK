package crabster.rudakov.sberschoollesson19hwk.data.model

data class CountryInfo (
    val names: Names,
    val maps: Maps,
    val timezone: Timezone,
    val telephone: Telephone,
)

data class Names (
    val name: String,
    val full: String
    )

data class Maps (
    val lat: Float,
    val long: Float
)

data class Timezone (
    val name: String,
)

data class Telephone (
    val calling_code: String,
)