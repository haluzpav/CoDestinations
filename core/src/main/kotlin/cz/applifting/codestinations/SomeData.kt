package cz.applifting.codestinations

import java.io.Serializable

data class SomeData(
    val name: String,
    val phone: Long,
) : Serializable
