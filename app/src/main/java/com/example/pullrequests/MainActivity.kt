package com.example.pullrequests

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pullrequests.adapters.PullRequestAdapter
import com.example.pullrequests.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var requestsAdapter: PullRequestAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        collectAndObserveState()
        setupViews()
    }

    private fun setupRecyclerView() {
        with(binding.pullRequestsRv) {
            requestsAdapter = PullRequestAdapter()
            adapter = requestsAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun collectAndObserveState() {
        lifecycleScope.launchWhenCreated {
            requestsAdapter.loadStateFlow.collect {
                // Loading
                val isLoading =
                    it.source.refresh is LoadState.Loading || it.source.append is LoadState.Loading
                binding.swipeRefreshLayout.isRefreshing = isLoading
                if (isLoading) {
                    binding.errorTv.visibility = View.GONE
                } else {
                    binding.errorTv.visibility = View.VISIBLE
                }

                // Error
                val error = it.source.refresh is LoadState.Error
                if (error) {
                    binding.errorTv.visibility = View.VISIBLE
                    binding.errorTv.text = (it.source.refresh as LoadState.Error).error.message
                } else {
                    binding.errorTv.visibility = View.GONE
                    binding.errorTv.text = ""
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.requests.collect { pullRequests ->
                requestsAdapter.submitData(pullRequests)
            }
        }
    }

    private fun setupViews() {
        binding.swipeRefreshLayout.setOnRefreshListener { requestsAdapter.refresh() }
    }
}
