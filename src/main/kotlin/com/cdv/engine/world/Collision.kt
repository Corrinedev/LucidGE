package com.cdv.engine.world

import com.cdv.engine.entity.Entity

interface Collision {
    val id: String
    /**
     * Checks if the current position collides with another position.
     *
     * @param other The other position to check against.
     * @return true if there is a collision, false otherwise.
     */
    fun <E> collidesWith(other: E): Boolean

    /**
     * Checks if the current position collides with another entity.
     *
     * @param entity The entity to check against.
     * @return true if there is a collision, false otherwise.
     */
    fun collidesWith(entity: Entity): Boolean

}