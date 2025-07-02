package com.cdv.logic.manager

import com.cdv.entity.Entity
import com.cdv.logic.Updatable

object EntityManager : Updatable {
    private val entities = HashSet<Entity>()

    init {
        register()
    }

    fun register(entity: Entity) {
        entities.add(entity)
    }

    fun unregister(entity: Any) {
        entities.remove(entity)
    }

    override fun update() {
        for (entity in entities) {
            for(component in entity.components.values) {
                component.tick()
            }
        }
    }
}