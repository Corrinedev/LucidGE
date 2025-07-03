package com.cdv.engine.resources

import java.io.InputStream

object ResourceManager {
    fun grabInputStream(path: String): InputStream? {
        return ResourceManager::class.java.classLoader.getResourceAsStream(path)
    }
}