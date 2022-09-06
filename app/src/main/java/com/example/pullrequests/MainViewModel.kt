package com.example.pullrequests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pullrequests.model.PullRequest
import com.example.pullrequests.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {
    private val _requests = repository.getPullRequests()
        .flow
        .cachedIn(viewModelScope)
    val requests: Flow<PagingData<PullRequest>>
        get() = _requests
}
