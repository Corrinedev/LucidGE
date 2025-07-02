package com.cdv.components

import com.cdv.entity.Entity
import com.cdv.entity.Player
import org.joml.Vector2f

class PlayerComponent : Component<Player> {
    val momentum = Vector2f()
    override fun getOwner(): Player {
        return Player
    }

    override fun attach(connected: Player) {
    }

    override fun tick() {
        Player.lastX = Player.x
        Player.lastY = Player.y
        Player.x += momentum.x
        Player.y += momentum.y
        momentum.div(2f)
    }

    override fun detach() {
    }
}