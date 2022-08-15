package com.github.astat1cc.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.astat1cc.holybibleapp.R

class BibleAdapter(
    private val retry: Retry,
    private val collapseListener: CollapseListener
) : RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    fun update(newBooks: List<BookUi>) {
        val diffCallback = DiffUtilCallback(books, newBooks)
        val result = DiffUtil.calculateDiff(diffCallback)
        books.clear()
        books.addAll(newBooks)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) =
        when (books[position]) {
            is BookUi.Progress -> 0
            is BookUi.Base -> 1
            is BookUi.Testament -> 2
            is BookUi.Fail -> 3
            else -> -1
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            0 -> BibleViewHolder.FullscreenProgress(R.layout.progress_fullscreen.createView(parent))
            1 -> BibleViewHolder.Base(R.layout.book.createView(parent))
            2 -> BibleViewHolder.Testament(R.layout.testament.createView(parent), collapseListener)
            else -> BibleViewHolder.Fail(R.layout.fail_fullscreen.createView(parent), retry)
        }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size

    private fun Int.createView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)

    abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        open fun bind(book: BookUi) {}

        class FullscreenProgress(view: View) : BibleViewHolder(view)

        abstract class Info(view: View) : BibleViewHolder(view) {

            private val name = itemView.findViewById<TextView>(R.id.textView)

            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }

        class Base(view: View) : Info(view)

        class Testament(view: View, private val collapseListener: CollapseListener) : Info(view) {

            private val collapseStateIcon = itemView.findViewById<ImageView>(R.id.expandImageView)

            override fun bind(book: BookUi) {
                super.bind(book)

                itemView.setOnClickListener {
                    book.collapseOrExpand(collapseListener)
                }
                book.updateCollapseIcon(
                    object : BookUi.CollapseMapper {
                        override fun map(collapsed: Boolean) {
                            collapseStateIcon.setImageResource(
                                if (collapsed) {
                                    R.drawable.ic_baseline_expand_more_24
                                } else {
                                    R.drawable.ic_baseline_expand_less_24
                                }
                            )
                        }
                    }
                )
            }
        }

        class Fail(view: View, private val retry: Retry) : BibleViewHolder(view) {

            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)

            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                })

                button.setOnClickListener { retry.tryAgain() }
            }
        }
    }

    interface CollapseListener {

        fun collapseOrExpand(id: Int)
    }
}

interface Retry {

    fun tryAgain()
}