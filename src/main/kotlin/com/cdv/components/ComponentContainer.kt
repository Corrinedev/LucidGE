package com.cdv.components

interface ComponentContainer<Owner> {
    val components: HashMap<Class<out Component<out Owner>>, Component<out Owner>>

    fun <T : Component<out Owner>> getComponent(type: Class<T>): T? {
        @Suppress("UNCHECKED_CAST")
        return components[type] as? T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Owner> addComponent(component: Component<T>) {
        components[component::class.java] = component
        component.attach(this as T)
    }
}