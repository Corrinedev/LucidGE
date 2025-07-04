package com.cdv.lucid.player

import com.cdv.engine.components.CollisionComponent
import com.cdv.engine.components.MovementComponent
import com.cdv.lucid.player.PlayerComponent
import com.cdv.engine.entity.Entity
import com.cdv.engine.input.InputCallback
import com.cdv.engine.window.Window
import com.cdv.engine.world.AABB
import org.joml.Vector2f

object Player : Entity("player", Vector2f(-2f, -2f)), InputCallback {
   var lastX = 0f
   var lastY = 0f
   init {
      registerInput()
      addComponent(PlayerComponent())
      addComponent(MovementComponent())
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
      val playerComponent = getComponent(MovementComponent::class.java)
      playerComponent?.momentum?.add(x, y)
   }

   override fun collider(): AABB? {
      return null
   }
}