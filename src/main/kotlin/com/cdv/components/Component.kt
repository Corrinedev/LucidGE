package com.cdv.components

interface Component<Owner> {
    fun getOwner(): Owner

    fun attach(connected: Owner)

    fun tick()

    fun detach()
}