package com.cdv.engine.world

import com.cdv.engine.entity.Entity
import java.util.LinkedList
import kotlin.collections.HashSet

abstract class World {
    val objects: HashSet<WorldObject> = HashSet()
    val entities: HashSet<Entity> = HashSet()

    fun tick() {

    }
}