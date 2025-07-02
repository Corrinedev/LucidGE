package com.cdv.catalog

interface Catalog<R> {
    val registries: HashMap<String, Blueprint<R>>
    val active: HashMap<Int, R>

    fun create(id: String) : R? {
        val blueprint = registries[id] ?: return null
        val instance = blueprint.create()
        active[instance.hashCode()] = instance
        return instance
    }
}