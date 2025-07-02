package com.cdv.resources

object ResourceManager {
    fun grabInputStream(path: String): java.io.InputStream? {
        return ResourceManager::class.java.classLoader.getResourceAsStream(path)
    }
}