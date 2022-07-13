package io.github.t3m8ch.testdatabuilders.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class DoterNotFoundException(val id: UUID) : RuntimeException()
