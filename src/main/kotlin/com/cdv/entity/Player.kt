package com.cdv.entity

import com.cdv.components.PlayerComponent
import com.cdv.input.InputCallback
import com.cdv.window.Window
import com.cdv.window.render.Renderer
import com.cdv.window.sprite.Quad2D
import com.cdv.window.texture.TextureManager
import org.lwjgl.glfw.GLFW.*
import kotlin.concurrent.thread


object Player : Entity("player", 0f, 0f), InputCallback {
   var lastX = 0f
   var lastY = 0f
   init {
      registerInput()
      addComponent(PlayerComponent())
   }

   override fun keyCallback(key: Int, scancode: Int, pressed: Boolean, mods: Int) {
      if(!pressed) return
         //when (key) {
         //   GLFW_KEY_W -> addMomentum(0f, 1f * Window.deltaTime)
         //   GLFW_KEY_S -> addMomentum(0f, -1f * Window.deltaTime)
         //   GLFW_KEY_A -> addMomentum(-1f * Window.deltaTime, 0f)
         //   GLFW_KEY_D -> addMomentum(1f * Window.deltaTime, 0f)
         //}
   }

   fun addMomentum(x: Float, y: Float) {
      val playerComponent = getComponent(PlayerComponent::class.java)
      playerComponent?.momentum?.add(x, y)
   }
}