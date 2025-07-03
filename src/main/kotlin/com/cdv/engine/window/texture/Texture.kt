package com.cdv.engine.window.texture

class Texture(val glId: Int, val resource: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Texture

        if (glId != other.glId) return false
        if (resource != other.resource) return false

        return true
    }

    override fun hashCode(): Int {
        var result = glId
        result = 31 * result + resource.hashCode()
        return result
    }
}
