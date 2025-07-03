package com.cdv.lucid.map

import org.joml.Vector2f

data class Tile(val pos: Vector2f, val size: Vector2f, val textureId: Int,
                override var collision: Boolean
) : WorldObject {

    fun contains(point: Vector2f): Boolean {
        return point.x >= pos.x && point.x <= pos.x + size.x &&
               point.y >= pos.y && point.y <= pos.y + size.y
    }

    fun intersects(other: Tile): Boolean {
        return this.pos.x < other.pos.x + other.size.x &&
               this.pos.x + this.size.x > other.pos.x &&
               this.pos.y < other.pos.y + other.size.y &&
               this.pos.y + this.size.y > other.pos.y
    }

    override fun position(): Vector2f {
        return pos
    }

    override fun size(): Vector2f {
        return size
    }

    override fun texture(): Int {
        return textureId
    }
}
