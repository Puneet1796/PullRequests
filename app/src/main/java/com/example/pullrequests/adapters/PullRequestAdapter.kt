package com.example.pullrequests.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.pullrequests.model.PullRequest

class PullRequestAdapter :
    PagingDataAdapter<PullRequest, PullRequestListItemViewHolder>(PullRequest.CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PullRequestListItemViewHolder {
        return PullRequestListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PullRequestListItemViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}
