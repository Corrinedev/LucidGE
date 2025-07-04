package com.cdv.engine.window.sprite

import com.cdv.engine.window.Buffers
import com.cdv.engine.window.Camera
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_TEXTURE_2D
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30


data class Quad2D(val width: Float = 32f, val height: Float = 32f, var scale: Float = 50f, var center: Boolean = false) {
    val vao: Int = genVAO()
    var texture: Int = 0

    var vertices: FloatArray = if(center) floatArrayOf(
        (-width / 2f) / scale, (-height / 2f) / scale, 0f,
        (-width / 2f) / scale, (height / 2f) / scale, 0f,
        (width / 2f) / scale, (height / 2f) / scale, 0f,
        (width / 2f) / scale, (-height / 2f) / scale, 0f
    ) else floatArrayOf(
        0f, 0f, 0f,
        0f, height / scale, 0f,
        width / scale, height / scale, 0f,
        width / scale, 0f, 0f
    )

    var indices: IntArray = intArrayOf(
        0, 1, 2,
        0, 2, 3
    )

    val uvs = floatArrayOf(
        0f, 1f,
        0f, 0f,
        1f, 0f,
        1f, 1f,
    )

    init {
        storeData(0,3,vertices)
        storeData(1,2,uvs)
        bindIndices(indices)

        GL30.glBindVertexArray(0)
    }

    fun drawWithTexture(id: Int) {
        Camera.apply()
        GL30.glBindVertexArray(vao)
        GL20.glEnableVertexAttribArray(0)
        GL20.glEnableVertexAttribArray(1)
        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL11.glBindTexture(GL_TEXTURE_2D, id)
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertices.size, GL11.GL_UNSIGNED_INT,0)
        GL20.glDisableVertexAttribArray(0)
        GL20.glDisableVertexAttribArray(1)
        GL30.glBindVertexArray(0)
        Camera.end()
    }

    fun draw() {
        Camera.apply()
        GL30.glBindVertexArray(vao)
        GL20.glEnableVertexAttribArray(0)
        GL20.glEnableVertexAttribArray(1)
        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL11.glBindTexture(GL_TEXTURE_2D, texture)
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertices.size, GL11.GL_UNSIGNED_INT,0)
        GL20.glDisableVertexAttribArray(0)
        GL20.glDisableVertexAttribArray(1)
        GL30.glBindVertexArray(0)
        Camera.end()
    }

    fun addTexture(loadTexture: Int): Quad2D {
        texture = loadTexture
        return this
    }

    companion object {
        private fun genVAO(): Int {
            val vao: Int = GL30.glGenVertexArrays()
            GL30.glBindVertexArray(vao)
            return vao
        }

        private fun storeData(attribute: Int, dimensions: Int, data: FloatArray?) {
            val vbo = GL15.glGenBuffers() //Creates a VBO ID
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo) //Loads the current VBO to store the data
            val buffer = Buffers.createFloatBuffer(data!!)
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
            GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0)
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0) //Unloads the current VBO when done.
        }

        private fun bindIndices(data: IntArray?) {
            val vbo = GL15.glGenBuffers()
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo)
            val buffer = Buffers.createIntBuffer(data!!)
            GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        }
    }
}