package io.github.t3m8ch.testdatabuilders.integration.doters

import io.github.t3m8ch.testdatabuilders.ContextInitializer
import io.github.t3m8ch.testdatabuilders.builders.entities.doters
import io.github.t3m8ch.testdatabuilders.customAssertions.DotersOutDTOAssert
import io.github.t3m8ch.testdatabuilders.dto.CreateUpdateDoterDTO
import io.github.t3m8ch.testdatabuilders.exceptions.DoterNotFoundException
import io.github.t3m8ch.testdatabuilders.repositories.DoterRepository
import io.github.t3m8ch.testdatabuilders.services.DoterService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ContextConfiguration(initializers = [ContextInitializer::class])
@Transactional
class DoterServiceTest(
    @Autowired private val doterRepository: DoterRepository,
    @Autowired private val doterService: DoterService,
) {
    @Test
    fun `getAll() should return all doters`() {
        val doters = doters {
            that {
                hasCount(2)
                hasMother()
                didMotherGoToCinema()
            }
            that {
                hasCount(2)
                hasNoMother()
            }
        }
        doterRepository.saveAll(doters)

        val actualDoters = doterService.getAll()

        DotersOutDTOAssert.assertThat(actualDoters).hasCount(4)
        DotersOutDTOAssert.assertThat(actualDoters.slice(0..1)).hasMother().didMotherGoToCinema()
        DotersOutDTOAssert.assertThat(actualDoters.slice(2..3)).hasNoMother()
    }

    @Test
    fun `getById(id) should return doter by id`() {
        val doters = doters {
            that {
                hasCount(8)
            }
        }
        doterRepository.saveAll(doters)

        val actualDoter = doterService.getById(doters[0].id)
        assertThat(actualDoter.id).isEqualTo(doters[0].id)
    }

    @Test
    fun `getById(nonExistsId) should throw DoterNotFoundException`() {
        val doters = doters {
            that {
                hasCount(8)
            }
        }
        val savedDoters = doters.slice(0..6)
        doterRepository.saveAll(savedDoters)

        assertThatThrownBy { doterService.getById(doters[7].id) }.isInstanceOf(DoterNotFoundException::class.java)
    }

    @Test
    fun `create(doter) should create new doter`() {
        doterService.create(CreateUpdateDoterDTO(fullName = ""))
        assertThat(doterRepository.findAll().toList().size).isEqualTo(1)
    }

    @Test
    fun `updateById(id, doter) should update doter`() {
        val doters = doters {
            that {
                hasCount(2)
                hasMother()
                didMotherGoToCinema()
                withFullName("Vasya")
            }
        }
        doterRepository.saveAll(doters)

        val returnedDoter = doterService.updateById(
            doters[1].id,
            CreateUpdateDoterDTO(fullName = "Petya", hasMother = false, didMotherGoToCinema = false)
        )

        val notEditedDoter = doterRepository.findByIdOrNull(doters[0].id)
        val editedDoter = doterRepository.findByIdOrNull(doters[1].id)

        assertThat(returnedDoter.id).isEqualTo(editedDoter?.id)

        assertThat(editedDoter?.hasMother).isFalse
        assertThat(editedDoter?.didMotherGoToCinema).isFalse
        assertThat(editedDoter?.fullName).isEqualTo("Petya")

        assertThat(notEditedDoter?.hasMother).isTrue
        assertThat(notEditedDoter?.didMotherGoToCinema).isTrue
        assertThat(notEditedDoter?.fullName).isEqualTo("Vasya")
    }

    @Test
    fun `deleteById(id) should delete doter with this id`() {
        val doters = doters {
            that {
                hasCount(1)
                withFullName("Petya")
            }
            that {
                hasCount(1)
                withFullName("Vasya")
            }
        }
        doterRepository.saveAll(doters)

        val returnedDoter = doterService.deleteById(doters[0].id)

        val actualDoters = doterRepository.findAll().toList()
        assertThat(returnedDoter.id).isEqualTo(doters[0].id)
        assertThat(actualDoters.size).isEqualTo(1)
        assertThat(actualDoters[0].fullName).isEqualTo("Vasya")
    }

    @TestFactory
    fun `should throw DoterNotFoundException`(): List<DynamicTest> {
        val doters = doters {
            that {
                hasCount(8)
            }
        }
        val savedDoters = doters.slice(0..6)
        doterRepository.saveAll(savedDoters)

        return listOf(
            Pair("getById(nonExistsId) should throw DoterNotFoundException") {
                doterService.getById(doters[7].id)
            },
            Pair("updateById(nonExistsId) should throw DoterNotFoundException") {
                doterService.updateById(
                    doters[7].id,
                    CreateUpdateDoterDTO(fullName = "")
                )
            },
            Pair("deleteById(nonExistsId) should throw DoterNotFoundException") {
                doterService.deleteById(doters[7].id)
            },
        ).map { (displayName, callback) ->
            dynamicTest(displayName) {
                assertThatThrownBy { callback() }.isInstanceOf(DoterNotFoundException::class.java)
            }
        }
    }
}
