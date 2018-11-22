package com.adrianbukros.github.example

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import java.util.ArrayList

@ImplicitReflectionSerializer
class MainActivity : AppCompatActivity() {

    private val uiScope = CoroutineScope(Dispatchers.Main)

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

        GitHubApiClient(username, password).repos(
            successCallback = {
                uiScope.launch {
                    progressBar.visibility = View.GONE

                    val reposListParcelable = it.map { repo ->
                        GitHubRepoParc(repo.name, repo.htmlUrl)
                    }
                    startActivity(Intent(this@MainActivity, ListActivity::class.java)
                        .apply {
                            putParcelableArrayListExtra(
                                ListActivity.REPO_EXTRAS_KEY,
                                reposListParcelable as ArrayList<out Parcelable>
                            )
                        })
                }
            }, errorCallback = {
                uiScope.launch {
                    progressBar.visibility = View.GONE

                    errorText.visibility = View.VISIBLE
                    errorText.text = "Request failed! Error: ${it.message ?: "unknown"}"
                }
            })
    }
}
