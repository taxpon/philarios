package io.philarios.schema.entities.codegen.util

import io.philarios.schema.Field

val Field.actualSingularName
    get() = singularName ?: when {
        name.endsWith("ies") -> name.removeSuffix("ies").plus("y")
        else -> name.removeSuffix("s")
    }

val Field.escapedName
    get() = name.escaped
