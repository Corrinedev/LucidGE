package com.cdv.engine.components

interface Component<Owner> {
    fun getOwner(): Owner

    fun attach(connected: Owner)

    fun tick()

    fun detach()
}