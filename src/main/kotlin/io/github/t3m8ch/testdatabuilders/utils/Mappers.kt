package io.github.t3m8ch.testdatabuilders

import io.github.t3m8ch.testdatabuilders.dto.CreateUpdateDoterDTO
import io.github.t3m8ch.testdatabuilders.dto.DoterOutDTO

fun io.github.t3m8ch.testdatabuilders.entities.Doter.mapToOutDTO() = DoterOutDTO(id, fullName!!, hasMother!!, didMotherGoToCinema)

fun CreateUpdateDoterDTO.mapToEntity() = io.github.t3m8ch.testdatabuilders.entities.Doter(
    fullName = fullName,
    hasMother = hasMother,
    didMotherGoToCinema = didMotherGoToCinema
)
