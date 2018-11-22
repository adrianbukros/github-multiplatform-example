package com.adrianbukros.github.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.adrianbukros.github.example.R.id.repoRecyclerView
import kotlinx.android.synthetic.main.activity_list.repoRecyclerView

class ListActivity : AppCompatActivity() {

    companion object {
        const val REPO_EXTRAS_KEY = "repo_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "GitHub Repos"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setContentView(R.layout.activity_list)

        val reposList = intent.getParcelableArrayListExtra<GitHubRepoParc>(REPO_EXTRAS_KEY)

        repoRecyclerView.setHasFixedSize(true)

        val adapter = RepoListAdapter(reposList)
        repoRecyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}