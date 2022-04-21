package com.bignerdranch.android.rickandmorty

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addPaginationScrollListener(
    layoutManager: LinearLayoutManager,
    itemToLoad: Int,
    onLoadMore: () -> Unit,
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

            if(dy != 0 && totalItemCount <= (lastVisibleItem + itemToLoad)) {
                recyclerView.post(onLoadMore)
            }
        }
    })
}

fun RecyclerView.addSpaceDecoration(space: Int) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            outRect.bottom = space
            outRect.top = space
            outRect.left = space
            outRect.right = space
        }
    })
}