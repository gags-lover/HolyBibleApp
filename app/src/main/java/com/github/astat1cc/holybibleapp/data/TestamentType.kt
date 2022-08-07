package com.github.astat1cc.holybibleapp.data

enum class TestamentType(private val id: Int) {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    fun getId() = id
}