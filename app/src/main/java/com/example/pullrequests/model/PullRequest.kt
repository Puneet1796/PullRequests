package com.example.pullrequests.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class PullRequest(
    val id: Long,
    val title: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("closed_at") val closedAt: String,
    val user: RequestOwner
) {
    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<PullRequest>() {
            override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
                return oldItem == newItem
            }
        }
    }
}

data class RequestOwner(
    val id: Long,
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val userImageUrl: String
)
