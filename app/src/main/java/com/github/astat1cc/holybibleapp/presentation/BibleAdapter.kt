package com.github.astat1cc.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.astat1cc.holybibleapp.R

class BibleAdapter(
    private val retry: Retry
) : RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    fun update(newBooks: List<BookUi>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) =
        when (books[position]) {
            is BookUi.Progress -> 0
            is BookUi.Fail -> 1
            is BookUi.Base -> 2
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            0 -> BibleViewHolder.FullscreenProgress(R.layout.progress_fullscreen.createView(parent))
            1 -> BibleViewHolder.Fail(R.layout.fail_fullscreen.createView(parent), retry)
            else -> BibleViewHolder.Base(R.layout.book_layout.createView(parent))
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

        class Base(view: View) : BibleViewHolder(view) {

            private val name = itemView.findViewById<TextView>(R.id.textView)

            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
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
}

interface Retry {

    fun tryAgain()
}