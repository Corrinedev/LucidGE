package com.cdv.engine.input

object Mouse {
    var x: Double = 0.0
    var y: Double = 0.0
    var scrollX: Double = 0.0
    var scrollY: Double = 0.0
    var lastY: Double = 0.0
    var lastX: Double = 0.0
    var leftButton: Boolean = false
    var rightButton: Boolean = false
    var middleButton: Boolean = false
    var dragging: Boolean = false

    fun callback(xpos: Double, ypos: Double) {
        this.lastX = this.x
        this.lastY = this.y
        this.x = xpos
        this.y = ypos
    }

    fun pressCallback(button: Int) {
        when (button) {
            0 -> leftButton = !leftButton
            1 -> middleButton = !middleButton
            2 -> rightButton = !rightButton
        }
    }
}