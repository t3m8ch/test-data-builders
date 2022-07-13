package io.github.t3m8ch.testdatabuilders.builders.entities

import io.github.t3m8ch.testdatabuilders.entities.Doter

fun doters(configure: DotersBuilder.() -> Unit): List<Doter> {
    val builder = DotersBuilder()
    builder.configure()
    return builder.build()
}

class DoterThatElement : ThatElement() {
    var fullName: String = "Vasya"
        private set

    var hasMother: Boolean = false
        private set

    var didMotherGoToCinema: Boolean = true
        private set

    fun withFullName(fullName: String) {
        this.fullName = fullName
    }

    fun hasMother() {
        hasMother = true
    }

    fun hasNoMother() {
        hasMother = false
    }

    fun didMotherGoToCinema() {
        didMotherGoToCinema = true
    }

    fun didNotMotherGoToCinema() {
        didMotherGoToCinema = false
    }
}

class DotersBuilder : TestDataBuilder<DoterThatElement, Doter>({
    createThatElement = { DoterThatElement() }
    mapThatElementToObject = {
        Doter(
            fullName = it.fullName,
            hasMother = it.hasMother,
            didMotherGoToCinema = it.didMotherGoToCinema
        )
    }
})
