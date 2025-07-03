package com.cdv.lucid.player

import com.cdv.engine.components.CollisionComponent
import com.cdv.lucid.player.PlayerComponent
import com.cdv.engine.entity.Entity
import com.cdv.engine.input.InputCallback
import com.cdv.engine.window.Window
import com.cdv.engine.world.AABB

object Player : Entity("player", 0f, 0f), InputCallback {
   var lastX = 0f
   var lastY = 0f
   init {
      registerInput()
      addComponent(PlayerComponent())
      addComponent(CollisionComponent().apply {
         hitboxes.put("main", AABB(0f, 0f, 0.5f, 0.5f, "main"))
      })
      Window.registerRenderer { PlayerRenderer }
   }

   override fun keyCallback(key: Int, scancode: Int, pressed: Boolean, mods: Int) {
      //Runs on render thread, deltatime needed in here
      if(!pressed) return
   }

   fun addMomentum(x: Float, y: Float) {
      val playerComponent = getComponent(PlayerComponent::class.java)
      playerComponent?.momentum?.add(x, y)
   }
}