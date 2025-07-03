package com.cdv.engine.entity

import com.cdv.engine.components.Component
import com.cdv.engine.components.ComponentContainer
import com.cdv.engine.logic.manager.EntityManager
import com.cdv.engine.world.Position

open class Entity (
    val id: String,
    override var x: Float,
    override var y: Float,
    override val components: HashMap<String, Component<out Entity>> = HashMap()
) : Position, ComponentContainer<Entity> {

    init {
        EntityManager.register(this)
    }
}