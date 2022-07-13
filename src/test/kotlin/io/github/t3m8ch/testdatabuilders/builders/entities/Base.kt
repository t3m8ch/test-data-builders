package io.github.t3m8ch.testdatabuilders.builders.entities

abstract class ThatElement {
    var count: Int = 0
        private set

    fun hasCount(count: Int) {
        this.count = count
    }
}

abstract class TestDataBuilder<T : ThatElement, ObjectType>(
    configure: TestDataBuilder<T, ObjectType>.() -> Unit,
) {
    init {
        configure()
    }

    lateinit var createThatElement: () -> T
    lateinit var mapThatElementToObject: (T) -> ObjectType

    private val thatElements: MutableList<T> = mutableListOf()

    fun that(configure: T.() -> Unit) {
        val thatElement = createThatElement()
        thatElement.configure()
        thatElements += thatElement
    }

    fun build(): List<ObjectType> {
        val objects = mutableListOf<ObjectType>()
        for (thatElement in thatElements) {
            objects += (1..thatElement.count).map { mapThatElementToObject(thatElement) }
        }
        return objects
    }
}
