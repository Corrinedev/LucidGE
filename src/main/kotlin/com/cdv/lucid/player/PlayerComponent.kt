package com.cdv.lucid.player

import com.cdv.engine.components.Component
import com.cdv.engine.input.Keyboard
import org.joml.Vector2f
import org.lwjgl.glfw.GLFW

class PlayerComponent : Component<Player> {
    val momentum = Vector2f()
    override fun getOwner(): Player {
        return Player
    }

    override fun attach(connected: Player) {
    }

    override fun tick() {
        if(Keyboard.keymap[GLFW.GLFW_KEY_W] ?: false) {
            momentum.add(0f, 0.005f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_S] ?: false) {
            momentum.add(0f, -0.005f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_A] ?: false) {
            momentum.add(-0.005f, 0f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_D] ?: false) {
            momentum.add(0.005f, 0f)
        }

        Player.lastX = Player.x
        Player.lastY = Player.y
        Player.x += momentum.x
        Player.y += momentum.y
        momentum.div(1.25f)
    }

    override fun detach() {
    }
}