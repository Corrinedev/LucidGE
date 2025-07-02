package com.cdv.window

import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f
import java.util.Vector

object Camera {
    var translation: Vector3f = Vector3f()
    var rotation: Quaternionf = Quaternionf()
    var scale: Float = 50f

    fun getProjectionMatrix(): Matrix4f {
        val matrix = Matrix4f().ortho2D(
            (-Window.width / 2).toFloat(),
            (Window.width / 2).toFloat(),
            (-Window.height / 2).toFloat(),
            (Window.height / 2).toFloat()
        ).translate(translation).scale(scale).rotate(rotation)
        return matrix
    }

    fun translate(xyz: Vector3f) {
        Window.shader.setUniform("projection", getProjectionMatrix().translate(xyz))
    }
}