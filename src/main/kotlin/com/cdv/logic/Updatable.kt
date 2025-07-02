package com.cdv.logic

interface Updatable {
    fun register() {
        GameLoop.register(this)
    }
    fun update()
}