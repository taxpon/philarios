package io.philarios.concourse.v0

import io.philarios.core.v0.emptyContext
import io.philarios.core.v0.map
import io.philarios.core.v0.unwrap
import io.philarios.schema.v0.translators.codegen.SchemaCodegen
import kotlinx.coroutines.runBlocking

suspend fun main() {
    emptyContext()
            .map(ConcourseSchemaSpec)
            .map(SchemaCodegen("./src/commonGenerated/kotlin"))
}