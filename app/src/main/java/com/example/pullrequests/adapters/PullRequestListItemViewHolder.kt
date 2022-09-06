package com.example.pullrequests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pullrequests.R
import com.example.pullrequests.databinding.LayoutPullRequestListItemBinding
import com.example.pullrequests.model.PullRequest
import com.example.pullrequests.utils.getFormattedDate
import com.example.pullrequests.utils.toDp

class PullRequestListItemViewHolder(
    private val binding: LayoutPullRequestListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(request: PullRequest) {
        with(binding) {
            titleTv.text = request.title
            openedAtValueTv.text = getFormattedDate(request.createdAt)
            closedAtValueTv.text = getFormattedDate(request.closedAt)
            ownerValueTv.text = request.user.userName
            Glide.with(root)
                .load(request.user.userImageUrl)
                .placeholder(R.drawable.img_loading_bg)
                .circleCrop()
                .override(root.context.toDp(60), root.context.toDp(60))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ownerIv)
        }
    }

    companion object {
        fun from(parent: ViewGroup): PullRequestListItemViewHolder =
            PullRequestListItemViewHolder(
                LayoutPullRequestListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
    }
}
