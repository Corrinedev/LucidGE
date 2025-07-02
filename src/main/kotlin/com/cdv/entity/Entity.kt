package com.cdv.entity

import com.cdv.components.Component
import com.cdv.components.ComponentContainer
import com.cdv.logic.manager.EntityManager
import com.cdv.world.AABB
import com.cdv.world.Collision
import com.cdv.world.CollisionHolder
import com.cdv.world.Position

open class Entity(
    val id: String,
    override var x: Float,
    override var y: Float,
    vararg hitboxArgs: AABB,
    override val components: HashMap<Class<out Component<out Entity>>, Component<out Entity>> = HashMap()
) : Position, CollisionHolder, ComponentContainer<Entity> {

    val hitboxes: MutableMap<String, AABB> = mutableMapOf()
    val primaryHitbox: AABB get() = hitboxes.values.firstOrNull() ?: AABB(0f, 0f, 0f, 0f, id)

    init {
        for (aABB in hitboxArgs) {
            this.hitboxes[aABB.id] = aABB
        }
        EntityManager.register(this)
    }

    override fun getCollider(id: String): Collision? {
        return hitboxes[id]
    }
}