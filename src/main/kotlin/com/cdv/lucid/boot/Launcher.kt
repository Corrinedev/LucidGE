package com.cdv.lucid.boot

import com.cdv.engine.logic.GameLoop
import com.cdv.engine.window.Window
import com.cdv.engine.world.AABB
import com.cdv.engine.world.World
import com.cdv.lucid.map.Tile
import com.cdv.lucid.player.Player
import org.joml.Vector2f


fun main() {
    Player
    GameLoop.world = object : World() {}
    Thread(Window, "Render Thread").start()
    Thread(GameLoop, "Logic Thread").start()
    GameLoop.world.objects.add(Tile(AABB(-1f, 0f, 0.5f, 0.5f, ""), 0, true))
    GameLoop.world.objects.add(Tile(AABB(1f, 0f, 1.5f, 0.5f, ""), 0, true))
}