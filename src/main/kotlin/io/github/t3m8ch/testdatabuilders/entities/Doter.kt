package io.github.t3m8ch.testdatabuilders.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "doter")
class Doter(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "full_name", nullable = false)
    var fullName: String? = null,

    @Column(name = "has_mother", nullable = false)
    var hasMother: Boolean = false,

    @Column(name = "did_mother_go_to_cinema", nullable = false)
    var didMotherGoToCinema: Boolean = true,
) {
    override fun toString() =
        "Doter(id=$id, fullName=$fullName, hasMother=$hasMother, didMotherGoToCinema=$didMotherGoToCinema)"
}
