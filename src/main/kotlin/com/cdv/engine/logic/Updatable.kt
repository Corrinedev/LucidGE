package com.cdv.engine.logic

interface Updatable {
    fun register() {
        GameLoop.register(this)
    }
    fun update()
}