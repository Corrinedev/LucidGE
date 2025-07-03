package com.cdv.lucid.map

import org.joml.Vector2f

interface WorldObject {
    fun position(): Vector2f
    fun size(): Vector2f
    fun texture(): Int
    var collision: Boolean
}