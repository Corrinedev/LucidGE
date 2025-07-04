package com.cdv.engine.components

import com.cdv.engine.entity.Entity
import com.cdv.engine.logic.GameLoop
import com.cdv.engine.world.AABB
import org.joml.Vector2f
import kotlin.math.absoluteValue

class MovementComponent : Component<Entity> {
    lateinit var ownedBy: Entity
    val momentum = Vector2f()
    var friction = 1.2f
    var wallfriction = 1.5f
    override fun getOwner(): Entity {
        return ownedBy
    }

    override fun attach(connected: Entity) {
        ownedBy = connected
    }

    override fun tick() {
        var collide = false
        ownedBy.getComponent(CollisionComponent::class.java)?.apply {
            for(hitbox in hitboxes.values) {
                hitbox.moveTo(ownedBy.position.x + momentum.x, ownedBy.position.y)
                if(hitbox.intersectsAnyDistance(GameLoop.world, ((hitbox.size().x + hitbox.size().y)) * 2, ownedBy.position) != null) {
                    momentum.x = 0f
                    collide = true
                }
                hitbox.moveTo(ownedBy.position.x, ownedBy.position.y + momentum.y)
                if(hitbox.intersectsAnyDistance(GameLoop.world, ((hitbox.size().x + hitbox.size().y)) * 2, ownedBy.position) != null) {
                    momentum.y = 0f
                    collide = true
                }
            }
        }

        ownedBy.position.add(momentum)
        momentum.div(collide.let { if(it) wallfriction else friction })
    }

    override fun detach() {
        ownedBy.removeComponent(this)
    }

}