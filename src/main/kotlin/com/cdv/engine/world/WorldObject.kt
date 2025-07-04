package com.cdv.engine.world

import org.joml.Vector2f

interface WorldObject {
    fun collider(): AABB?
    fun position(): Vector2f

}