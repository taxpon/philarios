package io.philarios.schema.entities.codegen.types.builder

import com.squareup.kotlinpoet.TypeSpec
import io.philarios.schema.RefType
import io.philarios.schema.Type
import io.philarios.schema.Union

typealias UnionBuilderTypeSpec = Union.(typeRefs: Map<RefType, Type>) -> List<TypeSpec>

fun createUnionBuilderTypeSpec(
        structBuilderTypeSpec: StructBuilderTypeSpec
): UnionBuilderTypeSpec = { typeRefs ->
    shapes.mapNotNull { structBuilderTypeSpec.invoke(it, typeRefs) }
}
