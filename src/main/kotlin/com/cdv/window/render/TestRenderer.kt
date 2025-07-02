package com.cdv.window.render

import com.cdv.window.Window
import com.cdv.window.sprite.Quad2D
import com.cdv.window.texture.TextureManager

object TestRenderer : Renderer {
    val sprite: Quad2D = Quad2D().addTexture(TextureManager.loadTexture("tile_testing.png"))
    var x: Float = 0f
    override fun render() {
        for(i in 0 until 10000)
        sprite.draw(x, 0f)
    }

    init {
        Window.renderers.add(TestRenderer)
    }
}