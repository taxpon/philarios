package io.philarios.structurizr

import io.philarios.core.DslBuilder
import kotlin.String
import kotlin.collections.Iterable
import kotlin.collections.List

@DslBuilder
interface WorkspaceBuilder<out C> {
    val context: C

    fun name(value: String)

    fun description(value: String)

    fun model(body: ModelBuilder<C>.() -> Unit)

    fun model(spec: ModelSpec<C>)

    fun model(ref: ModelRef)

    fun model(value: Model)

    fun include(body: WorkspaceBuilder<C>.() -> Unit)

    fun include(spec: WorkspaceSpec<C>)

    fun <C2> include(context: C2, body: WorkspaceBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: WorkspaceSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: WorkspaceBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: WorkspaceSpec<C2>)
}

@DslBuilder
interface ModelBuilder<out C> {
    val context: C

    fun person(body: PersonBuilder<C>.() -> Unit)

    fun person(spec: PersonSpec<C>)

    fun person(ref: PersonRef)

    fun person(value: Person)

    fun people(people: List<Person>)

    fun softwareSystem(body: SoftwareSystemBuilder<C>.() -> Unit)

    fun softwareSystem(spec: SoftwareSystemSpec<C>)

    fun softwareSystem(ref: SoftwareSystemRef)

    fun softwareSystem(value: SoftwareSystem)

    fun softwareSystems(softwareSystems: List<SoftwareSystem>)

    fun include(body: ModelBuilder<C>.() -> Unit)

    fun include(spec: ModelSpec<C>)

    fun <C2> include(context: C2, body: ModelBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: ModelSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: ModelBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: ModelSpec<C2>)
}

@DslBuilder
interface PersonBuilder<out C> {
    val context: C

    fun id(value: String)

    fun name(value: String)

    fun description(value: String)

    fun location(value: Location)

    fun include(body: PersonBuilder<C>.() -> Unit)

    fun include(spec: PersonSpec<C>)

    fun <C2> include(context: C2, body: PersonBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: PersonSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: PersonBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: PersonSpec<C2>)
}

@DslBuilder
interface SoftwareSystemBuilder<out C> {
    val context: C

    fun id(value: String)

    fun name(value: String)

    fun description(value: String)

    fun location(value: Location)

    fun container(body: ContainerBuilder<C>.() -> Unit)

    fun container(spec: ContainerSpec<C>)

    fun container(ref: ContainerRef)

    fun container(value: Container)

    fun containers(containers: List<Container>)

    fun include(body: SoftwareSystemBuilder<C>.() -> Unit)

    fun include(spec: SoftwareSystemSpec<C>)

    fun <C2> include(context: C2, body: SoftwareSystemBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: SoftwareSystemSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: SoftwareSystemBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: SoftwareSystemSpec<C2>)
}

@DslBuilder
interface ContainerBuilder<out C> {
    val context: C

    fun id(value: String)

    fun name(value: String)

    fun description(value: String)

    fun technology(value: String)

    fun component(body: ComponentBuilder<C>.() -> Unit)

    fun component(spec: ComponentSpec<C>)

    fun component(ref: ComponentRef)

    fun component(value: Component)

    fun components(components: List<Component>)

    fun include(body: ContainerBuilder<C>.() -> Unit)

    fun include(spec: ContainerSpec<C>)

    fun <C2> include(context: C2, body: ContainerBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: ContainerSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: ContainerBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: ContainerSpec<C2>)
}

@DslBuilder
interface ComponentBuilder<out C> {
    val context: C

    fun id(value: String)

    fun name(value: String)

    fun description(value: String)

    fun technology(value: String)

    fun relationship(body: RelationshipBuilder<C>.() -> Unit)

    fun relationship(spec: RelationshipSpec<C>)

    fun relationship(ref: RelationshipRef)

    fun relationship(value: Relationship)

    fun relationships(relationships: List<Relationship>)

    fun include(body: ComponentBuilder<C>.() -> Unit)

    fun include(spec: ComponentSpec<C>)

    fun <C2> include(context: C2, body: ComponentBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: ComponentSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: ComponentBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: ComponentSpec<C2>)
}

@DslBuilder
interface RelationshipBuilder<out C> {
    val context: C

    fun destinationId(value: String)

    fun description(value: String)

    fun technology(value: String)

    fun interactionStyle(value: InteractionStyle)

    fun include(body: RelationshipBuilder<C>.() -> Unit)

    fun include(spec: RelationshipSpec<C>)

    fun <C2> include(context: C2, body: RelationshipBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: RelationshipSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: RelationshipBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: RelationshipSpec<C2>)
}
