package com.cdv.lucid.map

import com.cdv.engine.window.Camera
import com.cdv.engine.window.render.Renderer
import com.cdv.engine.window.sprite.Quad2D
import org.joml.Vector3f

object TileRenderer : Renderer {
    val tileQuad: Quad2D = Quad2D(32f, 32f)
    val tiles: HashSet<WorldObject> = HashSet()
    override fun render() {
        for(tile in tiles) {
            Camera.translate(tile.position().let { Vector3f(it.x, it.y, 0f) })
            tileQuad.drawWithTexture(tile.texture())
        }
    }


}