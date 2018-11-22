package com.adrianbukros.github.example

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_repo.view.repoLabel

class RepoListAdapter(private val githubRepos: List<GitHubRepoParc>) : RecyclerView.Adapter<RepoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoListViewHolder(view)
    }

    override fun getItemCount() = githubRepos.size

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        val repo = githubRepos[position]
        holder.itemView.repoLabel.text = repo.name
        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(repo.htmlUrl)
            holder.itemView.context.startActivity(browserIntent)
        }
    }

}

class RepoListViewHolder(labelView: View) : RecyclerView.ViewHolder(labelView)