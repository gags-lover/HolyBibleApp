package com.github.astat1cc.holybibleapp.presentation

import android.content.Context

interface CollapsedTestamentsCache {

    fun save(id: Int)

    fun read(): Set<Int>

    fun start()

    fun finish()

    class Base(context: Context) : CollapsedTestamentsCache {

        private val cachedIds = mutableSetOf<Int>()

        private val sharedPref = context.getSharedPreferences(ID_LIST_NAME, Context.MODE_PRIVATE)

        override fun save(id: Int) {
            cachedIds.add(id)
        }

        override fun read(): Set<Int> {
            val set = sharedPref.getStringSet(ID_LIST_KEY, emptySet()) ?: emptySet()
            return set.map { it.toInt() }.toSet()
        }

        override fun finish() {
            val idsSet = cachedIds.map { it.toString() }.toSet()
            sharedPref.edit().putStringSet(ID_LIST_KEY, idsSet).apply()
        }

        override fun start() {
            cachedIds.clear()
        }

        companion object {

            const val ID_LIST_NAME = "collapsedCacheIdList"
            const val ID_LIST_KEY = "collapsedCacheIdKey"
        }
    }
}