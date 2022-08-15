package com.github.astat1cc.holybibleapp.presentation

import com.github.astat1cc.holybibleapp.core.Abstract
import com.github.astat1cc.holybibleapp.core.Matcher

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper>(), RecyclerViewItem, Collapsable,
    Matcher<Int> {

    override fun map(mapper: StringMapper) {}

    override fun matches(arg: Int): Boolean = false

    open fun withChangedState(): BookUi = Empty

    open fun saveId(collapsedTestamentsCache: CollapsedTestamentsCache) = Unit


    object Empty : BookUi()

    object Progress : BookUi()

    abstract class Info(
        protected open val id: Int, // will be used to get chapters
        protected open val name: String
    ) : BookUi() {

        override fun map(mapper: StringMapper) {
            mapper.map(name)
        }
    }

    class Base(
        override val id: Int,
        override val name: String
    ) : Info(id, name) {

        override fun isSameWith(otherBook: BookUi): Boolean =
            (otherBook is Base) && (id == otherBook.id)

        override fun isContentSameWith(otherBook: BookUi): Boolean =
            (otherBook is Base) && (name == otherBook.name)
    }

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false
    ) : Info(id, name) {

        override fun collapseOrExpand(collapseListener: BibleAdapter.CollapseListener) {
            collapseListener.collapseOrExpand(id)
        }

        override fun matches(arg: Int) = arg == id

        override fun withChangedState(): BookUi = Testament(id, name, !collapsed)

        override fun isCollapsed(): Boolean = collapsed

        override fun isSameWith(otherBook: BookUi): Boolean =
            (otherBook is Testament) && (id == otherBook.id)

        override fun isContentSameWith(otherBook: BookUi): Boolean =
            (otherBook is Testament) && (name == otherBook.name) && (collapsed == otherBook.collapsed)

        override fun saveId(collapsedTestamentsCache: CollapsedTestamentsCache) {
            collapsedTestamentsCache.save(id)
        }

        override fun updateCollapseIcon(mapper: CollapseMapper) {
            mapper.map(collapsed)
        }
    }

    class Fail(private val message: String) : BookUi() {

        override fun map(mapper: StringMapper) {
            mapper.map(message)
        }
    }

    interface CollapseMapper {

        fun map(collapsed: Boolean)
    }

    //todo improve later
    interface StringMapper : Abstract.Mapper {

        fun map(text: String)
    }
}

interface Collapsable {

    fun isCollapsed(): Boolean = false

    fun collapseOrExpand(collapseListener: BibleAdapter.CollapseListener) {}

    fun updateCollapseIcon(mapper: BookUi.CollapseMapper) = Unit
}

interface RecyclerViewItem {

    fun isSameWith(otherBook: BookUi): Boolean = false

    fun isContentSameWith(otherBook: BookUi): Boolean = false
}