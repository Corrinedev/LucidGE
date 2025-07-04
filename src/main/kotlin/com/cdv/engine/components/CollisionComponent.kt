package com.cdv.engine.components

import com.cdv.engine.entity.Entity
import com.cdv.engine.logic.GameLoop
import com.cdv.engine.world.AABB

class CollisionComponent : Component<Entity> {
    lateinit var ownedBy: Entity
    var isColliding: Boolean = false
    var collidingBox: AABB? = null
    val hitboxes: MutableMap<String, AABB> = mutableMapOf()
    var callback: Function2<AABB, AABB, Unit> = { ab1, ab2 -> }

    override fun getOwner(): Entity {
        return ownedBy
    }

    override fun attach(connected: Entity) {
        this.ownedBy = connected
    }

    override fun tick() {
        for(hitbox in hitboxes.values) {
            hitbox.moveTo(ownedBy.position.x, ownedBy.position.y)
            val aabb: AABB? = hitbox.intersectsAnyDistance(GameLoop.world, (((hitbox.maxX - hitbox.minX) + (hitbox.maxY - hitbox.minY)) / 2) * 5f, ownedBy.position())
            if(aabb != null) {
                callback.invoke(hitbox, aabb)
            }
        }
    }

    override fun detach() {
        ownedBy.removeComponent(this)
    }
}