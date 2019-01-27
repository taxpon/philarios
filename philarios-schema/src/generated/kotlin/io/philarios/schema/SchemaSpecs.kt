package io.philarios.schema

import io.philarios.core.Scaffold
import io.philarios.core.Spec

class SchemaSpec<in C>(internal val body: SchemaBuilder<C>.() -> Unit) : Spec<C, Schema> {
    override fun connect(context: C): Scaffold<Schema> {
        val builder = SchemaShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class StructSpec<in C>(internal val body: StructBuilder<C>.() -> Unit) : Spec<C, Struct> {
    override fun connect(context: C): Scaffold<Struct> {
        val builder = StructShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class UnionSpec<in C>(internal val body: UnionBuilder<C>.() -> Unit) : Spec<C, Union> {
    override fun connect(context: C): Scaffold<Union> {
        val builder = UnionShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class EnumTypeSpec<in C>(internal val body: EnumTypeBuilder<C>.() -> Unit) : Spec<C, EnumType> {
    override fun connect(context: C): Scaffold<EnumType> {
        val builder = EnumTypeShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class RefTypeSpec<in C>(internal val body: RefTypeBuilder<C>.() -> Unit) : Spec<C, RefType> {
    override fun connect(context: C): Scaffold<RefType> {
        val builder = RefTypeShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class OptionTypeSpec<in C>(internal val body: OptionTypeBuilder<C>.() -> Unit) : Spec<C, OptionType> {
    override fun connect(context: C): Scaffold<OptionType> {
        val builder = OptionTypeShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class ListTypeSpec<in C>(internal val body: ListTypeBuilder<C>.() -> Unit) : Spec<C, ListType> {
    override fun connect(context: C): Scaffold<ListType> {
        val builder = ListTypeShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class MapTypeSpec<in C>(internal val body: MapTypeBuilder<C>.() -> Unit) : Spec<C, MapType> {
    override fun connect(context: C): Scaffold<MapType> {
        val builder = MapTypeShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

class FieldSpec<in C>(internal val body: FieldBuilder<C>.() -> Unit) : Spec<C, Field> {
    override fun connect(context: C): Scaffold<Field> {
        val builder = FieldShellBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}