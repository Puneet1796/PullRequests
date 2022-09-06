package com.example.pullrequests.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pullrequests.adapters.PullRequestPagingSource
import com.example.pullrequests.model.PullRequest
import com.example.pullrequests.network.NetworkInterface
import com.example.pullrequests.utils.PAGE_SIZE
import javax.inject.Inject

class Repository @Inject constructor(
    private val network: NetworkInterface
) {
    fun getPullRequests(): Pager<Int, PullRequest> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            PullRequestPagingSource(network)
        }
    }
}
