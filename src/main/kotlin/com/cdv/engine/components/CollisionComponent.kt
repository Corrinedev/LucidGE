package com.cdv.engine.components

import com.cdv.engine.entity.Entity
import com.cdv.engine.world.AABB

class CollisionComponent : Component<Entity> {
    lateinit var ownedBy: Entity
    var isColliding: Boolean = false
    val hitboxes: MutableMap<String, AABB> = mutableMapOf()

    override fun getOwner(): Entity {
        return ownedBy
    }

    override fun attach(connected: Entity) {
        this.ownedBy = connected
    }

    override fun tick() {
        for(hitbox in hitboxes.values) {
            hitbox.moveTo(ownedBy.x, ownedBy.y)
        }

    }

    override fun detach() {
        ownedBy.removeComponent(this)
    }
}