package com.cdv.engine.logic.manager

import com.cdv.engine.entity.Entity
import com.cdv.engine.logic.Updatable

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