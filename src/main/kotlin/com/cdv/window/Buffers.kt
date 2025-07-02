package com.cdv.window

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.nio.FloatBuffer
import java.nio.IntBuffer


object Buffers {
    val vbos = mutableListOf<Int>()
    val vaos = mutableListOf<Int>()
    fun createFloatBuffer(data: FloatArray): FloatBuffer {
        val buffer = BufferUtils.createFloatBuffer(data.size)
        buffer.put(data)
        buffer.flip()
        return buffer
    }

    fun createIntBuffer(data: IntArray): IntBuffer {
        val buffer = BufferUtils.createIntBuffer(data.size)
        buffer.put(data)
        buffer.flip()
        return buffer
    }

    fun storeData(attribute: Int, dimensions: Int, data: FloatArray?) {
        val vbo = GL15.glGenBuffers() //Creates a VBO ID
        vbos.add(vbo)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo) //Loads the current VBO to store the data
        val buffer = createFloatBuffer(data!!)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0) //Unloads the current VBO when done.
    }

    fun bindIndices(data: IntArray?) {
        val vbo = GL15.glGenBuffers()
        vbos.add(vbo)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo)
        val buffer = createIntBuffer(data!!)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
    }

    fun genVAO(): Int {
        val vao = GL30.glGenVertexArrays()
        vaos.add(vao)
        GL30.glBindVertexArray(vao)
        return vao
    }
}