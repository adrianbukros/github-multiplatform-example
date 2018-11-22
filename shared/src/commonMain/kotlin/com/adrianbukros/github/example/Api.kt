package com.adrianbukros.github.example

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON

internal expect val ApplicationDispatcher: CoroutineDispatcher

class GitHubApiClient(private val githubUserName: String, private val githubPassword: String) {

    private val httpClient = HttpClient()

    fun repos(successCallback: (List<GitHubRepo>) -> Unit, errorCallback: (Exception) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                try {
                    var reposString = httpClient.get<String> {
                        url {
                            protocol = URLProtocol.HTTPS
                            port = 443
                            host = "api.github.com"
                            encodedPath = "user/repos"
                            header("Authorization", "Basic " + "$githubUserName:$githubPassword".toBase64())
                        }
                    }
                    reposString = "{\"repos\":$reposString}"
                    val repos = JSON(strictMode = false).parse(Repos.serializer(), reposString).repos
                    successCallback(repos)
                } catch (ex: Exception) {
                    errorCallback(ex)
                }
            }
        }
    }
}

@Serializable
data class GitHubRepo(val name: String,
                      @SerialName("html_url")
                      val htmlUrl: String)

@Serializable
data class Repos(val repos: List<GitHubRepo>)
