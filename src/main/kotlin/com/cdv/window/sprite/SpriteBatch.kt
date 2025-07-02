package com.cdv.window.sprite

import com.cdv.window.Camera
import com.cdv.window.Window.quadTest
import com.cdv.window.texture.Texture
import org.joml.Vector2f
import org.joml.Vector3f
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_BLEND
import org.lwjgl.opengl.GL11.GL_CULL_FACE
import org.lwjgl.opengl.GL11.GL_DEPTH_TEST
import org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA
import org.lwjgl.opengl.GL11.GL_SRC_ALPHA
import org.lwjgl.opengl.GL11.GL_TEXTURE_2D
import org.lwjgl.opengl.GL11.glBlendFunc
import org.lwjgl.opengl.GL11.glDisable
import org.lwjgl.opengl.GL11.glEnable
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

data class Sprite(val texture: Int, val quad: Quad2D, val xSize: Int = 32, val ySize: Int = 32) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sprite

        if (texture != other.texture) return false
        if (xSize != other.xSize) return false
        if (ySize != other.ySize) return false

        return true
    }

    override fun hashCode(): Int {
        var result = texture.hashCode()
        result = 31 * result + xSize
        result = 31 * result + ySize
        return result
    }
    fun draw(x: Float, y: Float) {


    }
}
