package com.github.astat1cc.holybibleapp.presentation

interface UiDataCache {

    fun cache(books: List<BookUi>): BooksUi

    fun changeState(id: Int): List<BookUi>

    fun saveState()

    class Base(private val collapsedTestamentsCache: CollapsedTestamentsCache) : UiDataCache {

        private val cachedList = ArrayList<BookUi>()

        override fun cache(books: List<BookUi>): BooksUi {
            cachedList.clear()
            cachedList.addAll(books)
            var newList = books
            val collapsedTestamentsIds = collapsedTestamentsCache.read()
            collapsedTestamentsIds.forEach { id ->
                newList = changeState(id)
            }
            return BooksUi.Base(newList)
        }

        override fun changeState(id: Int): List<BookUi> {
            val newList = ArrayList<BookUi>()
            val testamentToChange = cachedList.find { it.matches(id) }

            var add = false
            cachedList.forEachIndexed { i, book ->
                if (book is BookUi.Testament) {
                    if (book == testamentToChange) {
                        val changedTestament = testamentToChange.withChangedState()
                        cachedList[i] = changedTestament
                        newList.add(changedTestament)
                        add = !changedTestament.isCollapsed()
                    } else {
                        newList.add(book)
                        add = !book.isCollapsed()
                    }
                } else {
                    if (add) newList.add(book)
                }
            }

            return newList
        }

        override fun saveState() {
            collapsedTestamentsCache.start()
            cachedList.filter { it.isCollapsed() }.forEach { collapsedTestament ->
                collapsedTestament.saveId(collapsedTestamentsCache)
            }
            collapsedTestamentsCache.finish()
        }
    }
}