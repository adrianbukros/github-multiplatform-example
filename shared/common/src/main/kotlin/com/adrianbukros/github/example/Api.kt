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

    fun repos(successCallback: (List<GitHubRepo>) -> Unit, errorCallback: (Exception) -> Unit) {
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
                successCallback(repos)
            } catch (ex: Exception) {
                errorCallback(ex)
            }
        }
    }
}

data class GitHubRepo(val name: String, val htmlUrl: String)