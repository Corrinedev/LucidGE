package com.cdv.engine.world

import com.cdv.engine.components.CollisionComponent
import com.cdv.engine.entity.Entity
import com.cdv.engine.window.Window
import com.cdv.engine.window.render.HitboxRenderer
import org.joml.Vector2f

data class AABB(var minX: Float, var minY: Float, var maxX: Float, var maxY: Float, override val id: String) : Collision {
    val worldPos: Vector2f = Vector2f(0f, 0f)
    init {
        Window.registerRenderer { HitboxRenderer(this) } //debug
        println("AABB created with id: $id")
    }
    fun intersects(other: AABB): Boolean {
        return this.minX < other.maxX && this.maxX > other.minX &&
                this.minY < other.maxY && this.maxY > other.minY
    }

    fun contains(point: Position): Boolean {
        return point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY
    }

    override fun <E> collidesWith(other: E): Boolean {
        return when (other) {
            is AABB -> this.intersects(other)
            is Position -> this.contains(other)
            is Entity -> this.collidesWith(other)
            else -> false
        }
    }

    override fun collidesWith(other: Entity): Boolean {
        if(!other.hasComponent(CollisionComponent::class.java)) {
            return false
        }
        val collisionHolder: CollisionComponent = other.getComponent(CollisionComponent::class.java)!!
        for (hitbox in collisionHolder.hitboxes.values) {
            if (this.intersects(hitbox)) {
                return true
            }
        }
        return false
    }

    fun move(dx: Float, dy: Float) {
        minX += dx
        minY += dy
        maxX += dx
        maxY += dy
        worldPos.add(dx, dy)
    }

    fun expand(amount: Float) {
        minX -= amount
        minY -= amount
        maxX += amount
        maxY += amount
    }

    fun multiply(factor: Float) {
        val centerX = (minX + maxX) / 2
        val centerY = (minY + maxY) / 2
        val halfWidth = (maxX - minX) * factor / 2
        val halfHeight = (maxY - minY) * factor / 2
        minX = centerX - halfWidth
        minY = centerY - halfHeight
        maxX = centerX + halfWidth
        maxY = centerY + halfHeight
    }

    fun moveTo(newX: Float, newY: Float) {
        val dx = newX - minX
        val dy = newY - minY
        move(dx, dy)
    }

    fun push(entity: Entity) {
        if(!entity.hasComponent(CollisionComponent::class.java)) return
        val collisionHolder: CollisionComponent = entity.getComponent(CollisionComponent::class.java)!!

        for (hitbox in collisionHolder.hitboxes.values) {
            if (this.intersects(hitbox)) {
                // Calculate overlap amounts
                val overlapX = minOf(this.maxX - hitbox.minX, hitbox.maxX - this.minX)
                val overlapY = minOf(this.maxY - hitbox.minY, hitbox.maxY - this.minY)

                // Separate along the axis with smallest overlap (shortest separation)
                if (overlapX < overlapY) {
                    // Separate horizontally
                    if (this.worldPos.x < hitbox.worldPos.x) {
                        // Push this hitbox left
                        move(-overlapX, 0f)
                    } else {
                        // Push this hitbox right
                        move(overlapX, 0f)
                    }
                } else {
                    // Separate vertically
                    if (this.worldPos.y < hitbox.worldPos.y) {
                        // Push this hitbox down
                        move(0f, -overlapY)
                    } else {
                        // Push this hitbox up
                        move(0f, overlapY)
                    }
                }
            }
        }
    }

    fun calculateWorldPosition(): Vector2f {
        return worldPos
    }
}
