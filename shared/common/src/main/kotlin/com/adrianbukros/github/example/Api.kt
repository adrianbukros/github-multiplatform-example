package com.adrianbukros.github.example

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.util.encodeBase64
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.launch
import kotlinx.serialization.json.JsonTreeParser
import kotlinx.serialization.json.content

internal expect val ApplicationDispatcher: CoroutineDispatcher

class GitHubApiClient(private val githubUserName: String, private val githubPassword: String) {

    private val httpClient = HttpClient()

    fun repos(callback: (RepoResult) -> Unit) {
        launch(ApplicationDispatcher) {
            try {
                val result: String = httpClient.get {
                    url {
                        protocol = URLProtocol.HTTPS
                        port = 443
                        host = "api.github.com"
                        encodedPath = "user/repos"
                        header("Authorization", "Basic " + "$githubUserName:$githubPassword".encodeBase64())
                    }
                }

                val repos = JsonTreeParser(result).read().jsonArray
                        .map { it.jsonObject }
                        .map { GitHubRepo(it["name"].content, it["html_url"].content) }
                callback(RepoResult(repos))
            } catch (ex: Exception) {
                callback(RepoResult(ex))
            }
        }
    }
}

data class GitHubRepo(val name: String, val htmlUrl: String)

class RepoResult {
    val repos: List<GitHubRepo>?
    val error: Exception?

    constructor(repos: List<GitHubRepo>) {
        this.repos = repos
        this.error = null
    }

    constructor(exception: Exception) {
        this.repos = null
        this.error = exception
    }
}
