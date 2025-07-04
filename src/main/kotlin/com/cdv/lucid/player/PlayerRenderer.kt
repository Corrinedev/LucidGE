package com.cdv.lucid.player

import com.cdv.engine.window.Camera
import com.cdv.engine.window.render.Renderer
import com.cdv.engine.window.sprite.Quad2D
import com.cdv.engine.window.texture.TextureManager
import org.joml.Vector3f

object PlayerRenderer : Renderer {
    val sprite: Quad2D = Quad2D(width = 0.5f, height = 0.5f, scale = 1f, center = false).addTexture(TextureManager.loadTexture("tile_testing.png"))
    override fun render() {
        Camera.translate(Vector3f(Player.position.x, Player.position.y, 0f))
        sprite.draw()
    }

    override fun priority(): Int {
        return 20
    }
}