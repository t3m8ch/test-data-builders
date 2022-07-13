package io.github.t3m8ch.testdatabuilders.controllers

import io.github.t3m8ch.testdatabuilders.dto.CreateUpdateDoterDTO
import io.github.t3m8ch.testdatabuilders.services.DoterService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("doters")
class DoterController(private val doterService: DoterService) {
    @GetMapping
    fun getAll() = doterService.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: UUID) = doterService.getById(id)

    @PostMapping
    fun create(@RequestBody doter: CreateUpdateDoterDTO) = doterService.create(doter)

    @PutMapping("{id}")
    fun updateById(@PathVariable id: UUID, @RequestBody doter: CreateUpdateDoterDTO) =
        doterService.updateById(id, doter)

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: UUID) = doterService.deleteById(id)
}
