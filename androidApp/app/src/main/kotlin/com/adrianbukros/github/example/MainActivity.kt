package com.adrianbukros.github.example

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "GitHub Login"

        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener { login() }
    }

    private fun login() {
        errorText.visibility = View.GONE

        val username = usernameEdit.text.toString()

        if (username.isEmpty()) {
            errorText.visibility = View.VISIBLE
            errorText.text = "Please fill the username!"
            return
        }

        val password = passwordEdit.text.toString()

        if (password.isEmpty()) {
            errorText.visibility = View.VISIBLE
            errorText.text = "Please fill the password!"
            return
        }

        progressBar.visibility = View.VISIBLE

        GitHubApiClient(username, password).repos {
            launch(UI) {
                progressBar.visibility = View.GONE

                it.error?.let { error ->
                    errorText.visibility = View.VISIBLE
                    errorText.text = "Request failed! Error: ${error.message ?: "unknown"}"
                }

                it.repos?.let { repoList ->
                    val reposListParcelable = repoList.map { repo ->
                        GitHubRepoParc(repo.name, repo.htmlUrl)
                    }

                    startActivity(Intent(this@MainActivity, ListActivity::class.java)
                            .apply { putParcelableArrayListExtra(ListActivity.REPO_EXTRAS_KEY, reposListParcelable as ArrayList<out Parcelable>) })
                }
            }
        }
    }
}
