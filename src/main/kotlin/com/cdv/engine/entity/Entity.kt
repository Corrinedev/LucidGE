package com.cdv.engine.entity

import com.cdv.engine.components.Component
import com.cdv.engine.components.ComponentContainer
import com.cdv.engine.logic.manager.EntityManager
import com.cdv.engine.world.AABB
import com.cdv.engine.world.Position
import com.cdv.engine.world.WorldObject
import org.joml.Vector2f

abstract class Entity (
    val id: String,
    val position: Vector2f,
    override val components: HashMap<String, Component<out Entity>> = HashMap()
) : WorldObject, ComponentContainer<Entity> {

    override fun position(): Vector2f {
        return position
    }

    init {
        EntityManager.register(this)
    }
}