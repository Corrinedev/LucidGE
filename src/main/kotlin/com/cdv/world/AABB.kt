package com.cdv.world

import com.cdv.entity.Entity

data class AABB(var minX: Float, var minY: Float, var maxX: Float, var maxY: Float, override val id: String) : Collision {

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
        for (hitbox in other.hitboxes.values) {
            if (this.intersects(hitbox as AABB)) {
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
}
