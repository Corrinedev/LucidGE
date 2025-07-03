package com.cdv.engine.window.shader

import org.joml.Matrix4f
import org.joml.Vector4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.net.URISyntaxException
import kotlin.io.path.Path
import kotlin.system.exitProcess

open class Shader(filename: String) {
    private var programObject: Int = GL20.glCreateProgram()
    private var vertexShaderObject: Int = GL20.glCreateShader(GL20.GL_VERTEX_SHADER)
    private var fragmentShaderObject: Int

    init {
        GL20.glShaderSource(vertexShaderObject, readFile("$filename.vs"))
        GL20.glCompileShader(vertexShaderObject)
        if (GL20.glGetShaderi(vertexShaderObject, GL20.GL_COMPILE_STATUS) != 1) {
            System.err.println(GL20.glGetShaderInfoLog(vertexShaderObject))
            exitProcess(1)
        }

        fragmentShaderObject = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER)
        GL20.glShaderSource(fragmentShaderObject, readFile("$filename.fs"))
        GL20.glCompileShader(fragmentShaderObject)
        if (GL20.glGetShaderi(fragmentShaderObject, GL20.GL_COMPILE_STATUS) != 1) {
            System.err.println(GL20.glGetShaderInfoLog(fragmentShaderObject))
            exitProcess(1)
        }

        GL20.glAttachShader(programObject, vertexShaderObject)
        GL20.glAttachShader(programObject, fragmentShaderObject)

        GL20.glBindAttribLocation(programObject, 0, "vertices")
        GL20.glBindAttribLocation(programObject, 1, "textures")

        GL20.glLinkProgram(programObject)
        if (GL20.glGetProgrami(programObject, GL20.GL_LINK_STATUS) != 1) {
            System.err.println(GL20.glGetProgramInfoLog(programObject))
            exitProcess(1)
        }
        GL20.glValidateProgram(programObject)
        if (GL20.glGetProgrami(programObject, GL20.GL_VALIDATE_STATUS) != 1) {
            System.err.println(GL20.glGetProgramInfoLog(programObject))
            exitProcess(1)
        }
    }

    @Throws(Throwable::class)
    protected fun detatch() {
        GL20.glDetachShader(programObject, vertexShaderObject)
        GL20.glDetachShader(programObject, fragmentShaderObject)
        GL20.glDeleteShader(vertexShaderObject)
        GL20.glDeleteShader(fragmentShaderObject)
        GL20.glDeleteProgram(programObject)
    }

    fun setUniform(uniformName: String, value: Int) {
        val location = GL20.glGetUniformLocation(programObject, uniformName)
        if (location != -1) GL20.glUniform1i(location, value)
    }

    fun setUniform(uniformName: String, value: Vector4f) {
        val location = GL20.glGetUniformLocation(programObject, uniformName)
        if (location != -1) GL20.glUniform4f(location, value.x, value.y, value.z, value.w)
    }

    fun setUniform(uniformName: String, value: Matrix4f) {
        val location = GL20.glGetUniformLocation(programObject, uniformName)
        val matrixData = BufferUtils.createFloatBuffer(16)
        value.get(matrixData)
        if (location != -1) GL20.glUniformMatrix4fv(location, false, matrixData)
    }

    fun bind() {
        GL20.glUseProgram(programObject)
    }

    private fun readFile(filename: String?): String {
        val outputString = StringBuilder()
        val bufferedReader: BufferedReader?
        try {
            val filePath = Path("assets/shaders/$filename").toUri()
            bufferedReader = BufferedReader(FileReader(File(filePath)))
            var line: String?
            while ((bufferedReader.readLine().also { line = it }) != null) {
                outputString.append(line)
                outputString.append("\n")
            }
            bufferedReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        return outputString.toString()
    }
}