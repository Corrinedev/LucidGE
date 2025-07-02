package com.cdv.catalog

interface Blueprint<R> {
    fun create(): R
}