package io.philarios.structurizr

import io.philarios.core.Translator
import com.structurizr.Workspace as SWorkspace
import com.structurizr.model.Model as SModel
import com.structurizr.model.Location as SLocation

class OutputTranslator : Translator<Workspace, SWorkspace> {
    override fun translate(context: Workspace) = context.convert()

}

private fun Workspace.convert() = SWorkspace(name, description).also {
    model?.let { model ->
        model.people.forEach { person ->
            it.model.addPerson(
                    person.location?.convert() ?: SLocation.Unspecified,
                    person.name,
                    person.description
            )
        }
        model.softwareSystems.forEach { softwareSystem ->
            val addedSoftwareSystem = it.model.addSoftwareSystem(
                    softwareSystem.location?.convert() ?: SLocation.Unspecified,
                    softwareSystem.name,
                    softwareSystem.description
            )
            softwareSystem.containers.forEach { container ->
                val addedContainer = addedSoftwareSystem.addContainer(
                        container.name,
                        container.description,
                        container.technology
                )
                container.components.forEach { component ->
                    addedContainer.addComponent(
                            component.name,
                            component.description,
                            component.technology
                    )
                }
            }
        }
    }
}

private fun Model.convert(): Model {
    TODO("not implemented")
}

private fun Location.convert() = when (this) {
    Location.Internal -> SLocation.Internal
    Location.External -> SLocation.External
    Location.Unspecified -> SLocation.Unspecified
}