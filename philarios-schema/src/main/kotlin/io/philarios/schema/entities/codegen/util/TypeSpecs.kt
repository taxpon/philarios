package io.philarios.schema.entities.codegen.util

import com.squareup.kotlinpoet.*
import io.philarios.core.Scaffold
import io.philarios.schema.*
import kotlin.reflect.KClass

val Type.typeName: TypeName
    get() = when (this) {
        is Struct -> ClassName(pkg ?: "", name)
        is Union -> ClassName(pkg ?: "", name)
        is EnumType -> ClassName(pkg ?: "", name)
        is RefType -> ClassName(pkg ?: "", name)
        is OptionType -> type.nullableTypeName
        is BooleanType -> Boolean::class.className
        is DoubleType -> Double::class.className
        is FloatType -> Float::class.className
        is LongType -> Long::class.className
        is IntType -> Int::class.className
        is ShortType -> Short::class.className
        is ByteType -> Byte::class.className
        is CharacterType -> Character::class.className
        is StringType -> String::class.className
        is AnyType -> Any::class.className
        is ListType -> ParameterizedTypeName.get(List::class.className, type.typeName)
        is MapType -> ParameterizedTypeName.get(Map::class.className, keyType.typeName, valueType.typeName)
    }

val Type.nullableTypeName
    get() = typeName.asNullable()

val Type.className: ClassName
    get() = when (this) {
        is Struct -> ClassName(pkg ?: "", name)
        is Union -> ClassName(pkg ?: "", name)
        is EnumType -> ClassName(pkg ?: "", name)
        is RefType -> ClassName(pkg ?: "", name)
        is OptionType -> type.nullableClassName
        is BooleanType -> Boolean::class.className
        is DoubleType -> Double::class.className
        is FloatType -> Float::class.className
        is LongType -> Long::class.className
        is IntType -> Int::class.className
        is ShortType -> Short::class.className
        is ByteType -> Byte::class.className
        is CharacterType -> Character::class.className
        is StringType -> String::class.className
        is AnyType -> Any::class.className
        is ListType -> List::class.className
        is MapType -> Map::class.className
    }

val Type.nullableClassName
    get() = className.asNullable()

fun Type.className(suffix: String) = when (this) {
    is Struct -> ClassName(pkg ?: "", "$name$suffix")
    is Union -> ClassName(pkg ?: "", "$name$suffix")
    is EnumType -> ClassName(pkg ?: "", "$name$suffix")
    is RefType -> ClassName(pkg ?: "", "$name$suffix")
    else -> className
}

val Type.shellClassName
    get() = className("Shell")

val Type.scaffoldTypeName: TypeName
    get() = when (this) {
        is OptionType -> type.scaffoldTypeName
        is ListType -> ParameterizedTypeName.get(
                List::class.className,
                type.scaffoldTypeName
        )
        is MapType -> ParameterizedTypeName.get(
                Map::class.className,
                keyType.scaffoldTypeName,
                valueType.scaffoldTypeName
        )
        else -> ParameterizedTypeName.get(Scaffold::class.className, className)
    }

val Type.wrapperTypeName
    get() = when (this) {
        is Union -> TypeVariableName("T")
        else -> typeName
    }

val Type.refTypeName
    get() = when (this) {
        is Union -> ParameterizedTypeName.get(refClassName, TypeVariableName("T"))
        else -> refClassName
    }

val Type.refClassName
    get() = className("Ref")

val Type.scaffolderTypeName
    get() = when (this) {
        is Union -> ParameterizedTypeName.get(scaffolderClassName, TypeVariableName("C", KModifier.IN), typeName)
        else -> ParameterizedTypeName.get(scaffolderClassName, TypeVariableName("C", KModifier.IN))
    }

val Type.scaffolderClassName
    get() = className("Scaffolder")

val Type.specTypeName
    get() = when (this) {
        is Union -> ParameterizedTypeName.get(specClassName, TypeVariableName("C", KModifier.IN), TypeVariableName("T"))
        else -> ParameterizedTypeName.get(specClassName, TypeVariableName("C", KModifier.IN))
    }

val Type.specClassName
    get() = className("Spec")

val Type.otherSpecTypeName
    get() = ParameterizedTypeName.get(className("Spec"), TypeVariableName("C2", KModifier.IN))

val Type.builderTypeName
    get() = ParameterizedTypeName.get(className("Builder"), TypeVariableName("C"))

val Type.otherBuilderTypeName
    get() = ParameterizedTypeName.get(className("Builder"), TypeVariableName("C2"))

val Type.shellBuilderTypeName
    get() = ParameterizedTypeName.get(className("ShellBuilder"), TypeVariableName("C"))

val Type.otherShellBuilderTypeName
    get() = ParameterizedTypeName.get(className("ShellBuilder"), TypeVariableName("C2"))

val Type.bodyLambdaTypeName
    get() = LambdaTypeName.get(builderTypeName, emptyList(), ClassName("", "Unit"))

val Type.otherBodyLambdaTypeName
    get() = LambdaTypeName.get(otherBuilderTypeName, emptyList(), ClassName("", "Unit"))

val KClass<*>.className get() = ClassName.bestGuess(qualifiedName!!)