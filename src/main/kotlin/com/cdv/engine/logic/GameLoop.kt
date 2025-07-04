package com.cdv.engine.logic

import com.cdv.engine.logic.manager.EntityManager
import com.cdv.engine.window.Window
import com.cdv.engine.world.World
import java.util.logging.Logger

object GameLoop : Runnable {
    private val updatables: HashSet<Updatable> = HashSet()
    private var running = false
    private var lastTime = System.nanoTime()
    private var TARGET_FPS: Long = 1
    private var TARGET_TIME: Long = (1_000_000_000L / TARGET_FPS)
    lateinit var world: World
    var ticks: Int = 0
    val LOGGER: Logger = Logger.getLogger(GameLoop::class.java.name)

    fun calculateTargetTime() {
        TARGET_FPS = Window.getFps()
        TARGET_TIME = (1_000_000_000L / TARGET_FPS)
    }

    //Primary issue with this implementation is that low physics tick rates could lead to slowdowns
    override fun run() {
        registerObjects()

        running = true
        lastTime = System.nanoTime()
        while (running) {
            val now = System.nanoTime()
            val elapsedTime = now - lastTime
            calculateTargetTime()
            if (elapsedTime >= TARGET_TIME) {
                if(elapsedTime != TARGET_TIME && elapsedTime > TARGET_TIME + 1_000_000L) LOGGER.warning { "GameLoop was ${((elapsedTime - TARGET_TIME) / 1_000_000_000F)}ms behind!" }
                ticks++
                update()
                lastTime = now
            }
        }
    }

    private fun registerObjects() {
        EntityManager
    }

    fun stop() {
        running = false
        Thread.currentThread().interrupt()
    }

    private fun update() {
        for (updatable in updatables) {
            try {
                updatable.update()
            } catch (e: Exception) {
                LOGGER.severe { "Error updating ${updatable::class.simpleName}: ${e.message}" }
            }
        }
    }

    fun register(updatable: Updatable) {
        this.updatables.add(updatable)
    }
}