package com.github.astat1cc.holybibleapp.presentation

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldList: List<BookUi>,
    private val newList: List<BookUi>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].isSameWith(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].isContentSameWith(newList[newItemPosition])
}