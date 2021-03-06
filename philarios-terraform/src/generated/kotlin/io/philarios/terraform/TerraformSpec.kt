// The builder interfaces needed to create type-safe specs.
//
// The specs and builders are located one layer below the model. While they need to reference the model classes
// for obvious reasons, they should still be as un-opinionated as possible and should not depend on any
// implementation details. This allows you to write specs without depending on how the specs are actually
// materialized.
//
// It is inevitable to take an at least somewhat opinionated approach while designing interfaces and some of the
// decisions reflect this. However, since comments or concerns are always welcome, please feel free to open an
// issue in the project's repository.
package io.philarios.terraform

import io.philarios.core.DslBuilder
import kotlin.Any
import kotlin.Pair
import kotlin.String
import kotlin.collections.Iterable
import kotlin.collections.List
import kotlin.collections.Map

class ConfigurationSpec<in C>(internal val body: ConfigurationBuilder<C>.() -> Unit)

@DslBuilder
interface ConfigurationBuilder<out C> {
    val context: C

    fun resource(body: ResourceBuilder<C>.() -> Unit)

    fun resource(spec: ResourceSpec<C>)

    fun resource(ref: ResourceRef)

    fun resource(value: Resource)

    fun resources(resources: List<Resource>)

    fun dataSource(body: DataSourceBuilder<C>.() -> Unit)

    fun dataSource(spec: DataSourceSpec<C>)

    fun dataSource(ref: DataSourceRef)

    fun dataSource(value: DataSource)

    fun dataSources(dataSources: List<DataSource>)

    fun provider(body: ProviderBuilder<C>.() -> Unit)

    fun provider(spec: ProviderSpec<C>)

    fun provider(ref: ProviderRef)

    fun provider(value: Provider)

    fun providers(providers: List<Provider>)

    fun variable(body: VariableBuilder<C>.() -> Unit)

    fun variable(spec: VariableSpec<C>)

    fun variable(ref: VariableRef)

    fun variable(value: Variable)

    fun variables(variables: List<Variable>)

    fun output(body: OutputBuilder<C>.() -> Unit)

    fun output(spec: OutputSpec<C>)

    fun output(ref: OutputRef)

    fun output(value: Output)

    fun outputs(outputs: List<Output>)

    fun include(body: ConfigurationBuilder<C>.() -> Unit)

    fun include(spec: ConfigurationSpec<C>)

    fun <C2> include(context: C2, body: ConfigurationBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: ConfigurationSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: ConfigurationBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: ConfigurationSpec<C2>)
}

class ConfigurationRef(internal val key: String)

class ResourceSpec<in C>(internal val body: ResourceBuilder<C>.() -> Unit)

@DslBuilder
interface ResourceBuilder<out C> {
    val context: C

    fun type(value: String)

    fun name(value: String)

    fun config(key: String, value: Any)

    fun config(pair: Pair<String, Any>)

    fun config(config: Map<String, Any>)

    fun include(body: ResourceBuilder<C>.() -> Unit)

    fun include(spec: ResourceSpec<C>)

    fun <C2> include(context: C2, body: ResourceBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: ResourceSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: ResourceBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: ResourceSpec<C2>)
}

class ResourceRef(internal val key: String)

class DataSourceSpec<in C>(internal val body: DataSourceBuilder<C>.() -> Unit)

@DslBuilder
interface DataSourceBuilder<out C> {
    val context: C

    fun type(value: String)

    fun name(value: String)

    fun config(key: String, value: Any)

    fun config(pair: Pair<String, Any>)

    fun config(config: Map<String, Any>)

    fun include(body: DataSourceBuilder<C>.() -> Unit)

    fun include(spec: DataSourceSpec<C>)

    fun <C2> include(context: C2, body: DataSourceBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: DataSourceSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: DataSourceBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: DataSourceSpec<C2>)
}

class DataSourceRef(internal val key: String)

class ProviderSpec<in C>(internal val body: ProviderBuilder<C>.() -> Unit)

@DslBuilder
interface ProviderBuilder<out C> {
    val context: C

    fun name(value: String)

    fun config(key: String, value: Any)

    fun config(pair: Pair<String, Any>)

    fun config(config: Map<String, Any>)

    fun include(body: ProviderBuilder<C>.() -> Unit)

    fun include(spec: ProviderSpec<C>)

    fun <C2> include(context: C2, body: ProviderBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: ProviderSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: ProviderBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: ProviderSpec<C2>)
}

class ProviderRef(internal val key: String)

class VariableSpec<in C>(internal val body: VariableBuilder<C>.() -> Unit)

@DslBuilder
interface VariableBuilder<out C> {
    val context: C

    fun name(value: String)

    fun type(value: String)

    fun default(value: Any)

    fun include(body: VariableBuilder<C>.() -> Unit)

    fun include(spec: VariableSpec<C>)

    fun <C2> include(context: C2, body: VariableBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: VariableSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: VariableBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: VariableSpec<C2>)
}

class VariableRef(internal val key: String)

class OutputSpec<in C>(internal val body: OutputBuilder<C>.() -> Unit)

@DslBuilder
interface OutputBuilder<out C> {
    val context: C

    fun name(value: String)

    fun value(value: Any)

    fun include(body: OutputBuilder<C>.() -> Unit)

    fun include(spec: OutputSpec<C>)

    fun <C2> include(context: C2, body: OutputBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: OutputSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: OutputBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: OutputSpec<C2>)
}

class OutputRef(internal val key: String)
