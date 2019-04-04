package io.philarios.structurizr

import io.philarios.core.Scaffold
import kotlin.String

class WorkspaceRef(key: String) : Scaffold<Workspace> by io.philarios.core.RegistryRef(io.philarios.structurizr.Workspace::class, key)

class ModelRef(key: String) : Scaffold<Model> by io.philarios.core.RegistryRef(io.philarios.structurizr.Model::class, key)

class PersonRef(key: String) : Scaffold<Person> by io.philarios.core.RegistryRef(io.philarios.structurizr.Person::class, key)

class SoftwareSystemRef(key: String) : Scaffold<SoftwareSystem> by io.philarios.core.RegistryRef(io.philarios.structurizr.SoftwareSystem::class, key)

class ContainerRef(key: String) : Scaffold<Container> by io.philarios.core.RegistryRef(io.philarios.structurizr.Container::class, key)

class ComponentRef(key: String) : Scaffold<Component> by io.philarios.core.RegistryRef(io.philarios.structurizr.Component::class, key)

class RelationshipRef(key: String) : Scaffold<Relationship> by io.philarios.core.RegistryRef(io.philarios.structurizr.Relationship::class, key)

class ViewSetRef(key: String) : Scaffold<ViewSet> by io.philarios.core.RegistryRef(io.philarios.structurizr.ViewSet::class, key)

class SystemLandscapeViewRef(key: String) : Scaffold<SystemLandscapeView> by io.philarios.core.RegistryRef(io.philarios.structurizr.SystemLandscapeView::class, key)

class SystemContextViewRef(key: String) : Scaffold<SystemContextView> by io.philarios.core.RegistryRef(io.philarios.structurizr.SystemContextView::class, key)

class ContainerViewRef(key: String) : Scaffold<ContainerView> by io.philarios.core.RegistryRef(io.philarios.structurizr.ContainerView::class, key)

class ComponentViewRef(key: String) : Scaffold<ComponentView> by io.philarios.core.RegistryRef(io.philarios.structurizr.ComponentView::class, key)

class DynamicViewRef(key: String) : Scaffold<DynamicView> by io.philarios.core.RegistryRef(io.philarios.structurizr.DynamicView::class, key)

class ConfigurationRef(key: String) : Scaffold<Configuration> by io.philarios.core.RegistryRef(io.philarios.structurizr.Configuration::class, key)

class BrandingRef(key: String) : Scaffold<Branding> by io.philarios.core.RegistryRef(io.philarios.structurizr.Branding::class, key)

class FontRef(key: String) : Scaffold<Font> by io.philarios.core.RegistryRef(io.philarios.structurizr.Font::class, key)

class StylesRef(key: String) : Scaffold<Styles> by io.philarios.core.RegistryRef(io.philarios.structurizr.Styles::class, key)

class ElementStyleRef(key: String) : Scaffold<ElementStyle> by io.philarios.core.RegistryRef(io.philarios.structurizr.ElementStyle::class, key)

class RelationshipStyleRef(key: String) : Scaffold<RelationshipStyle> by io.philarios.core.RegistryRef(io.philarios.structurizr.RelationshipStyle::class, key)

class TerminologyRef(key: String) : Scaffold<Terminology> by io.philarios.core.RegistryRef(io.philarios.structurizr.Terminology::class, key)

class WorkspaceConfigurationRef(key: String) : Scaffold<WorkspaceConfiguration> by io.philarios.core.RegistryRef(io.philarios.structurizr.WorkspaceConfiguration::class, key)

class UserRef(key: String) : Scaffold<User> by io.philarios.core.RegistryRef(io.philarios.structurizr.User::class, key)
