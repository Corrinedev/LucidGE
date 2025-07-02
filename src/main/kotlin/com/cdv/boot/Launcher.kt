package com.cdv.boot

import com.cdv.entity.Player
import com.cdv.logic.GameLoop
import com.cdv.window.Window
import com.cdv.window.render.TestRenderer
import org.lwjgl.opengl.GL


fun main() {
    Thread(Window, "Render Thread").start()
    Thread(GameLoop, "Logic Thread").start()
}