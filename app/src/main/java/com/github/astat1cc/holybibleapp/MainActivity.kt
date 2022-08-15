package com.github.astat1cc.holybibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.github.astat1cc.holybibleapp.presentation.BibleAdapter
import com.github.astat1cc.holybibleapp.presentation.MainViewModel
import com.github.astat1cc.holybibleapp.presentation.Retry
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter(
            object : Retry {
                override fun tryAgain() {
                    viewModel.fetchBooks()
                }
            },
            object : BibleAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) {
                    viewModel.collapseOrExpand(id)
                }
            }
        )
        with(recyclerView) {
            this.adapter = adapter
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            )
        }

        viewModel.observe(this) {
            adapter.update(it)
        }
        viewModel.fetchBooks()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveCollapsedState()
    }
}