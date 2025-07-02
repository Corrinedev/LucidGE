package com.cdv.input
val CALLBACKS: ArrayList<InputCallback> = ArrayList()

interface InputCallback {
    fun registerInput() {
        CALLBACKS.add(this)
    }
    fun mouseCallback(xpos: Double, ypos: Double){ }

    fun mousePressCallback(button: Int) { }

    fun mouseScrollCallback(xoffset: Double, yoffset: Double) { }

    fun keyCallback(key: Int, scancode: Int, pressed: Boolean, mods: Int) { }
}