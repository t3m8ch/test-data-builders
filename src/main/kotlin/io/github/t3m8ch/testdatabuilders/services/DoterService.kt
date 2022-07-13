package io.github.t3m8ch.testdatabuilders.services

import io.github.t3m8ch.testdatabuilders.dto.CreateUpdateDoterDTO
import io.github.t3m8ch.testdatabuilders.dto.DoterOutDTO
import io.github.t3m8ch.testdatabuilders.exceptions.DoterNotFoundException
import io.github.t3m8ch.testdatabuilders.mapToEntity
import io.github.t3m8ch.testdatabuilders.mapToOutDTO
import io.github.t3m8ch.testdatabuilders.repositories.DoterRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface DoterService {
    fun getAll(): List<DoterOutDTO>
    fun getById(id: UUID): DoterOutDTO
    fun create(dto: CreateUpdateDoterDTO): DoterOutDTO
    fun updateById(id: UUID, dto: CreateUpdateDoterDTO): DoterOutDTO
    fun deleteById(id: UUID): DoterOutDTO
}

@Service
@Transactional
class DoterServiceImpl(private val doterRepo: DoterRepository) : DoterService {
    override fun getAll(): List<DoterOutDTO> {
        return doterRepo.findAll().map { it.mapToOutDTO() }
    }

    override fun getById(id: UUID): DoterOutDTO {
        return doterRepo.findByIdOrNull(id)?.mapToOutDTO() ?: throw DoterNotFoundException(id)
    }

    override fun create(dto: CreateUpdateDoterDTO): DoterOutDTO {
        return doterRepo.save(dto.mapToEntity()).mapToOutDTO()
    }

    override fun updateById(id: UUID, dto: CreateUpdateDoterDTO): DoterOutDTO {
        val entity = doterRepo.findByIdOrNull(id) ?: throw DoterNotFoundException(id)

        entity.fullName = dto.fullName
        entity.hasMother = dto.hasMother
        entity.didMotherGoToCinema = dto.didMotherGoToCinema

        return doterRepo.save(entity).mapToOutDTO()
    }

    override fun deleteById(id: UUID): DoterOutDTO {
        val entity = doterRepo.findByIdOrNull(id) ?: throw DoterNotFoundException(id)
        doterRepo.delete(entity)
        return entity.mapToOutDTO()
    }
}
