package com.adrianbukros.github.example

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider

val kodein = Kodein {
    bind<String>("GitHubHost") with provider { "api.github.com" }
}
