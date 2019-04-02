package io.philarios.structurizr.entities

import com.structurizr.model.StaticStructureElement
import io.philarios.structurizr.*
import com.structurizr.Workspace as SWorkspace
import com.structurizr.model.Component as SComponent
import com.structurizr.model.Container as SContainer
import com.structurizr.model.InteractionStyle as SInteractionStyle
import com.structurizr.model.Location as SLocation
import com.structurizr.model.Model as SModel
import com.structurizr.model.Person as SPerson
import com.structurizr.model.SoftwareSystem as SSoftwareSystem
import com.structurizr.view.Border as SBorder
import com.structurizr.view.Branding as SBranding
import com.structurizr.view.Configuration as SConfiguration
import com.structurizr.view.ElementStyle as SElementStyle
import com.structurizr.view.Font as SFont
import com.structurizr.view.RelationshipStyle as SRelationshipStyle
import com.structurizr.view.Routing as SRouting
import com.structurizr.view.Shape as SShape
import com.structurizr.view.Styles as SStyles
import com.structurizr.view.Terminology as STerminology
import com.structurizr.view.ViewSet as SViewSet

typealias ElementMap = Map<String, StaticStructureElement>

fun Model.convert(model: SModel) = model.let {
    val map = collect(it)
    connect(map)
    map
}

private fun Model.collect(model: SModel) =
        emptyMap +
                people.fold(emptyMap) { map, person ->
                    map + person.collect(model.addPerson(
                            person.location?.collect() ?: SLocation.Unspecified,
                            person.name,
                            person.description
                    ))
                } +
                softwareSystems.fold(emptyMap) { map, softwareSystem ->
                    map + softwareSystem.collect(model.addSoftwareSystem(
                            softwareSystem.location?.collect() ?: SLocation.Unspecified,
                            softwareSystem.name,
                            softwareSystem.description
                    ))
                }

private fun Person.collect(person: SPerson) = mapOf(id to person)

private fun SoftwareSystem.collect(softwareSystem: SSoftwareSystem) =
        mapOf(id to softwareSystem) + containers.fold(emptyMap) { map, container ->
            map + container.collect(softwareSystem.addContainer(
                    container.name,
                    container.description,
                    container.technology
            ))
        }

private fun Container.collect(container: SContainer) =
        mapOf(id to container) + components.fold(emptyMap) { map, component ->
            map + component.collect(container.addComponent(
                    component.name,
                    component.description,
                    component.technology
            ))
        }

private fun Component.collect(component: SComponent) = mapOf(id to component)

private val emptyMap get() = emptyMap<String, StaticStructureElement>()

private fun Model.connect(map: ElementMap) {
    people.forEach { person -> person.connect(map) }
    softwareSystems.forEach { softwareSystem -> softwareSystem.connect(map) }
}

private fun Person.connect(map: ElementMap) {
    connectStaticStructureElements(map, id, relationships)
}

private fun SoftwareSystem.connect(map: ElementMap) {
    connectStaticStructureElements(map, id, relationships)
    containers.forEach { container -> container.connect(map) }
}

private fun Container.connect(map: ElementMap) {
    connectStaticStructureElements(map, id, relationships)
    components.forEach { component -> component.connect(map) }
}

private fun Component.connect(map: ElementMap) {
    connectStaticStructureElements(map, id, relationships)
}

private fun connectStaticStructureElements(
        map: ElementMap,
        id: String,
        relationships: List<Relationship>
) {
    map[id]?.let { source ->
        relationships.forEach { relationship ->
            map[relationship.destinationId]?.let { destination ->
                source.uses(
                        destination,
                        relationship.description,
                        relationship.technology,
                        relationship.interactionStyle.collect()
                )
            }
        }
    }
}

private fun Location.collect() = when (this) {
    Location.Internal -> SLocation.Internal
    Location.External -> SLocation.External
    Location.Unspecified -> SLocation.Unspecified
}

private fun InteractionStyle.collect() = when (this) {
    InteractionStyle.Synchronous -> SInteractionStyle.Synchronous
    InteractionStyle.Asynchronous -> SInteractionStyle.Asynchronous
}