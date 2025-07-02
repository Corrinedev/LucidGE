package com.cdv.components

import com.cdv.entity.Entity
import com.cdv.entity.Player
import com.cdv.input.Keyboard
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
            momentum.add(0f, 0.01f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_S] ?: false) {
            momentum.add(0f, -0.01f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_A] ?: false) {
            momentum.add(-0.01f, 0f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_D] ?: false) {
            momentum.add(0.01f, 0f)
        }

        Player.lastX = Player.x
        Player.lastY = Player.y
        Player.x += momentum.x
        Player.y += momentum.y
        momentum.div(2f)
    }

    override fun detach() {
    }
}