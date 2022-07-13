package io.github.t3m8ch.testdatabuilders.repositories

import io.github.t3m8ch.testdatabuilders.entities.Doter
import org.springframework.data.repository.CrudRepository
import java.util.*

interface DoterRepository : CrudRepository<Doter, UUID>
