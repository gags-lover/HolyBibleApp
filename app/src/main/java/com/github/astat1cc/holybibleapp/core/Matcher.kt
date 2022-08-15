package com.github.astat1cc.holybibleapp.core

interface Matcher<T> {

    fun matches(arg: T): Boolean
}