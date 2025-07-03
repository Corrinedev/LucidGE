package com.cdv.engine.window

import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f

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
        ).scale(scale).translate(translation).rotate(rotation)
        return matrix
    }

    fun scale(float: Float) {
        this.scale = float
    }

    fun translate(xyz: Vector3f) {
        this.translation = xyz
    }

    fun rotate(xyz: Quaternionf) {
        this.rotation = xyz
    }

    fun apply() {
        Window.shader.setUniform("projection", getProjectionMatrix())
    }

    fun end() {
        Window.shader.setUniform("projection", getProjectionMatrix())
        translation = Vector3f()
        rotation = Quaternionf()
        scale = 50f
    }
}