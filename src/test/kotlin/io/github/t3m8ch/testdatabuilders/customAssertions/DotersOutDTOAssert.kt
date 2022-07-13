package io.github.t3m8ch.testdatabuilders.customAssertions

import io.github.t3m8ch.testdatabuilders.dto.DoterOutDTO
import org.assertj.core.api.AbstractAssert

class DotersOutDTOAssert(actual: List<DoterOutDTO>) :
    AbstractAssert<DotersOutDTOAssert, List<DoterOutDTO>>(actual, DotersOutDTOAssert::class.java) {

    companion object {
        fun assertThat(actual: List<DoterOutDTO>) = DotersOutDTOAssert(actual)
    }

    fun hasCount(count: Int): DotersOutDTOAssert {
        val isTrue = actual.size == count
        if (!isTrue) failWithMessage("")
        return this
    }

    fun hasFullName(fullName: String): DotersOutDTOAssert {
        val isTrue = actual.all { it.fullName == fullName }
        if (!isTrue) failWithMessage("")
        return this
    }

    fun hasMother(): DotersOutDTOAssert {
        val isTrue = actual.all { it.hasMother }
        if (!isTrue) failWithMessage("")
        return this
    }

    fun hasNoMother(): DotersOutDTOAssert {
        val isTrue = actual.all { !it.hasMother }
        if (!isTrue) failWithMessage("")
        return this
    }

    fun didMotherGoToCinema(): DotersOutDTOAssert {
        val isTrue = actual.all { it.didMotherGoToCinema }
        if (!isTrue) failWithMessage("")
        return this
    }

    fun didNotMotherGoToCinema(): DotersOutDTOAssert {
        val isTrue = actual.all { !it.didMotherGoToCinema }
        if (!isTrue) failWithMessage("")
        return this
    }
}
