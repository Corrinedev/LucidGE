package com.cdv.lucid.map

import com.cdv.engine.world.AABB
import org.joml.Vector2f

data class Tile(val box: AABB, val textureId: Int,
                override var collision: Boolean
) : ITile {

    override fun collider(): AABB? {
        return box
    }

    override fun position(): Vector2f {
        return box.calculateWorldPosition()
    }

    override fun texture(): Int {
        return textureId
    }
}
