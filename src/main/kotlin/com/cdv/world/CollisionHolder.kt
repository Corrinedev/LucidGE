package com.cdv.world

interface CollisionHolder {
    fun getCollider(id: String): Collision?
}