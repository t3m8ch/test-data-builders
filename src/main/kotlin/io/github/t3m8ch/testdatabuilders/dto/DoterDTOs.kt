package io.github.t3m8ch.testdatabuilders.dto

import java.util.*

data class DoterOutDTO(
    val id: UUID,
    val fullName: String,
    val hasMother: Boolean,
    val didMotherGoToCinema: Boolean,
)

data class CreateUpdateDoterDTO(
    val fullName: String,
    val hasMother: Boolean = false,
    val didMotherGoToCinema: Boolean = true,
)
