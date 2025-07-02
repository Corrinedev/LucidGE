package com.cdv.input

import org.lwjgl.glfw.GLFW

object Keyboard {
    val keymap: MutableMap<Int, Boolean> = mutableMapOf()

    fun callback(key: Int, scancode: Int, action: Int, mods: Int) {
        val pressed: Boolean = when (action) {
            1 -> true // Key pressed
            0 -> false // Key released
            2 -> true
            else -> false
        }
        keymap[key] = pressed
    }

    fun keyHeld() {
        for(callback in CALLBACKS) {
            for((key, pressed) in keymap) {
                if(pressed) {
                    callback.keyCallback(key, 0, true, 0)
                }
            }
        }
    }
}