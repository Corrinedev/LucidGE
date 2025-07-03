package com.cdv.engine.components

import com.cdv.engine.entity.Entity

interface ComponentContainer<Owner> {
    val components: HashMap<String, Component<out Entity>>

    fun <T : Component<out Owner>> getComponent(type: Class<T>): T? {
        @Suppress("UNCHECKED_CAST")
        for(component in components.values) {
            if (type.isInstance(component)) {
                return component as T
            }
        }
        return null
    }

    fun <T : Component<out Owner>> getComponent(name: String): T? {
        @Suppress("UNCHECKED_CAST")
        return components[name] as? T
    }

    fun <T : Component<out Owner>> hasComponent(java: Class<T>): Boolean {
        for(component in components.values) {
            if (java.isInstance(component)) {
                return true
            }
        }
        return false
    }

    fun hasComponent(name: String): Boolean {
        return components.containsKey(name)
    }

    fun removeComponent(name: String) {
        components.remove(name)
    }

    fun removeComponent(component: Component<out Entity>) {
        components.remove(component::class.java.simpleName)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Owner> addComponent(component: Component<T>) {
        components[component::class.java.simpleName] = component as Component<out Entity>
        component.attach(this as T)
    }
}