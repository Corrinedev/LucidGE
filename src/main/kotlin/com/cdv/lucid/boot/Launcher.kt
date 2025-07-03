package com.cdv.lucid.boot

import com.cdv.engine.logic.GameLoop
import com.cdv.engine.window.Window
import com.cdv.lucid.player.Player


fun main() {
    Player
    Thread(Window, "Render Thread").start()
    Thread(GameLoop, "Logic Thread").start()
}