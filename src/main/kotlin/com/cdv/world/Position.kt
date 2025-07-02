package com.cdv.world

interface Position {
    val x: Float
    val y: Float

    fun distanceTo(other: Position): Float {
        val dx = other.x - this.x
        val dy = other.y - this.y
        return kotlin.math.sqrt(dx * dx + dy * dy)
    }

    fun isWithinDistance(other: Position, distance: Float): Boolean {
        return distanceTo(other) <= distance
    }
}