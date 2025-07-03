package com.cdv.engine.window.render

interface Renderer {
    fun render()
    fun priority(): Int {
        return 100
    }
}