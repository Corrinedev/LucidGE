package com.cdv.engine.world

interface CollisionHolder {
    fun getCollider(id: String): Collision?
}