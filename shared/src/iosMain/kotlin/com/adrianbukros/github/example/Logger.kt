package com.adrianbukros.github.example

import timber.log.*

actual fun initTimber() {
    Timber.plant(NSLogTree(2))
}
