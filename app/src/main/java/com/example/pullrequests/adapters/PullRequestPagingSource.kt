package com.example.pullrequests.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pullrequests.model.PullRequest
import com.example.pullrequests.network.NetworkInterface
import com.example.pullrequests.repository.NetworkExceptions
import com.example.pullrequests.utils.PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PullRequestPagingSource @Inject constructor(
    private val network: NetworkInterface
) : PagingSource<Int, PullRequest>() {
    override fun getRefreshKey(state: PagingState<Int, PullRequest>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PullRequest> {
        return try {
            val nextPageKey = params.key ?: 1
            val prevPage = if (nextPageKey == 1) null else nextPageKey
            // This a public repo because I didn't have one that has more than
            // 10 pull requests to show off the pagination
            val requests = network.getPullRequests(
                owner = "GetStream",
                repo = "Android-Samples",
                page = nextPageKey,
                pageSize = PAGE_SIZE
            )
            if (requests.isEmpty()) {
                LoadResult.Page(requests, prevPage, null)
            } else {
                LoadResult.Page(requests, prevPage, nextPageKey + 1)
            }
        } catch (e: HttpException) {
            LoadResult.Error(NetworkExceptions.Generic)
        } catch (e: IOException) {
            LoadResult.Error(NetworkExceptions.NoInternet)
        } catch (e: Exception) {
            LoadResult.Error(NetworkExceptions.Generic)
        }
    }
}
