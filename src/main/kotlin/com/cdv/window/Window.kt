package com.cdv.window

import com.cdv.entity.Player
import com.cdv.input.Keyboard
import com.cdv.input.Mouse
import com.cdv.window.Camera.getProjectionMatrix
import com.cdv.window.render.Renderer
import com.cdv.window.shader.TexturedShader
import com.cdv.window.sprite.Quad2D
import com.cdv.window.texture.TextureManager
import org.joml.Math
import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.system.exitProcess


object Window : Runnable {

    private lateinit var matrix: FloatBuffer
    var id: Long = 0L
    var width: Int = 640
    var height: Int = 480
    var aspectRatio: () -> Int = {(this.width / this.height)}
    var deltaTime: Float = 0f
    var lastFrameTime: Long = 0L
    val renderers: HashSet<Renderer> = HashSet()
    lateinit var shader: TexturedShader

    fun setup() {
        GLFWErrorCallback.createPrint(System.err).set()

        check(GLFW.glfwInit()) { "Unable to initialize GLFW" }

        GLFW.glfwDefaultWindowHints()
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

        id = GLFW.glfwCreateWindow(width, height, "Lucid", NULL, NULL)
        check(id !== NULL) { "Unable to create GLFW Window" }

        GLFW.glfwSetKeyCallback(id) { id: Long, key: Int, scancode: Int, action: Int, mods: Int -> Keyboard.callback(key, scancode, action, mods) }
        GLFW.glfwSetCursorPosCallback(id) { id: Long, xpos: Double, ypos: Double -> Mouse.callback(xpos, ypos) }

        stackPush().use { stack ->
            val pWidth: IntBuffer = stack.mallocInt(1)
            val pHeight: IntBuffer = stack.mallocInt(1)

            GLFW.glfwGetWindowSize(id, pWidth, pHeight)

            val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())

            GLFW.glfwSetWindowPos(
                id,
                (vidmode!!.width() - pWidth.get(0)) / 2,
                (vidmode.height() - pHeight.get(0)) / 2
            )

            GLFW.glfwMakeContextCurrent(id)
            GLFW.glfwSwapInterval(1)
            GLFW.glfwShowWindow(id)
        }

        GLFW.glfwSetWindowSizeCallback(id) { window: Long, width: Int, height: Int ->
            this.height = height
            this.width = width
            GL11.glViewport(0,0,this.width, this.height)
        }
        GLFW.glfwSwapInterval(0) // Disable VSync
        GL.createCapabilities()
        shader = TexturedShader()
    }

    override fun run() {

        setup()
        this.renderers.add(object : Renderer {
            val playerQuad: Quad2D = Quad2D().addTexture(TextureManager.loadTexture("tile_testing.png"))
            override fun render() {
                for(i in 0 until 100000)
                playerQuad.draw(Math.lerp(Player.lastX, Player.x, 0.5f), Math.lerp(Player.lastY, Player.y, 0.5f))
            }
        })
        loop()

        Callbacks.glfwFreeCallbacks(id)
        GLFW.glfwDestroyWindow(id)

        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)?.free()
    }
    fun loop() {

        while (!GLFW.glfwWindowShouldClose(id)) {
            inputLoop()

            preDelta()
            render()
            postDelta()
        }
        exitProcess(-1)
    }

    private fun postDelta() {
        deltaTime = (System.nanoTime() - lastFrameTime) / 1_000_000_000f
    }

    private fun preDelta() {
        val currentTime = System.nanoTime()
        lastFrameTime = currentTime
    }


    private fun render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

        shader.bind()

        shader.setUniform("projection", getProjectionMatrix())
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        for (renderer in renderers) {
            renderer.render()
        }

        GL20.glUseProgram(0)

        GLFW.glfwSwapBuffers(id)
    }

    private fun inputLoop() {
        Keyboard.keyHeld()
        GLFW.glfwPollEvents()
    }

    fun loadMatrix(location: Int, value: Matrix4f) {
        this.matrix = BufferUtils.createFloatBuffer(16)
        value.get(matrix)
        GL20.glUniformMatrix4fv(location, false, matrix)
    }

    fun getFps(): Long {
        return if (deltaTime == 0f) {
            (1L / deltaTime).toLong()
        } else {
            60
        }
    }
}