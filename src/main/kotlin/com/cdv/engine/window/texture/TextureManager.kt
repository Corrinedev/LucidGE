package com.cdv.engine.window.texture

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryStack
import java.io.File
import java.nio.ByteBuffer


object TextureManager {
    val textures = mutableMapOf<String, Texture>()

    fun loadTexture(resourceName: String): Int {
        val width: Int
        val height: Int
        val buffer: ByteBuffer?
        try {
            MemoryStack.stackPush().use { stack ->
                val w = stack.mallocInt(1)
                val h = stack.mallocInt(1)
                val channels = stack.mallocInt(1)

                val file = File("assets/$resourceName")
                println("Loading texture from: ${file.absolutePath}, it exists: ${file.exists()}")
                val filePath = file.absolutePath
                buffer = STBImage.stbi_load(filePath, w, h, channels, 4)
                if (buffer == null) {
                    throw Exception("Can't load file " + resourceName + " " + STBImage.stbi_failure_reason())
                }
                width = w.get()
                height = h.get()

                val id = GL11.glGenTextures()
                textures.put(resourceName, Texture(id, resourceName))
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, id)
                GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1)

                GL11.glTexImage2D(
                    GL11.GL_TEXTURE_2D,
                    0,
                    GL11.GL_RGBA,
                    width,
                    height,
                    0,
                    GL11.GL_RGBA,
                    GL11.GL_UNSIGNED_BYTE,
                    buffer
                )

                GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D)
                STBImage.stbi_image_free(buffer)
                return id
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }
}