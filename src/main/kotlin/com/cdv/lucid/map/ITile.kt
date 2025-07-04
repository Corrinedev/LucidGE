package com.cdv.lucid.map

import com.cdv.engine.world.WorldObject
import org.joml.Vector2f

interface ITile : WorldObject {
    fun texture(): Int
    var collision: Boolean
}