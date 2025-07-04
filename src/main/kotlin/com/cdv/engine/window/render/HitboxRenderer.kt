package com.cdv.engine.window.render

import com.cdv.engine.window.Camera
import com.cdv.engine.world.AABB
import org.joml.Vector3f
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.glBegin
import org.lwjgl.opengl.GL11.glEnd

class HitboxRenderer(val hitbox: AABB) : Renderer {

    override fun render() {
        Camera.apply()
        glBegin(GL11.GL_LINE_LOOP)
        hitbox.apply {
            GL11.glVertex2f(minX, minY)  // bottom-left
            GL11.glVertex2f(maxX, minY)  // bottom-right
            GL11.glVertex2f(maxX, maxY)  // top-right
            GL11.glVertex2f(minX, maxY)  // top-left
        }
        glEnd()
        Camera.end()
    }

    override fun priority(): Int {
        return 100
    }
}