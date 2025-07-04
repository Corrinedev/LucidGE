package com.cdv.lucid.player

import com.cdv.engine.components.CollisionComponent
import com.cdv.engine.components.Component
import com.cdv.engine.components.MovementComponent
import com.cdv.engine.input.Keyboard
import com.cdv.engine.logic.GameLoop
import com.cdv.engine.world.AABB
import org.joml.Vector2f
import org.lwjgl.glfw.GLFW
import kotlin.collections.emptyList

class PlayerComponent : Component<Player> {
    override fun getOwner(): Player {
        return Player
    }

    override fun attach(connected: Player) {
    }

    override fun tick() {
        val movement = Player.getComponent(MovementComponent::class.java)!!
        if(Keyboard.keymap[GLFW.GLFW_KEY_W] ?: false) {
            movement.momentum.add(0f, 0.005f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_S] ?: false) {
            movement.momentum.add(0f, -0.005f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_A] ?: false) {
            movement.momentum.add(-0.005f, 0f)
        }
        if(Keyboard.keymap[GLFW.GLFW_KEY_D] ?: false) {
            movement.momentum.add(0.005f, 0f)
        }
    }

    override fun detach() {
    }
}