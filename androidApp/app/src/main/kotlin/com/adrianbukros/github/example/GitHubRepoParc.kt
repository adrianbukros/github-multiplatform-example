package com.adrianbukros.github.example

import android.os.Parcel
import android.os.Parcelable

data class GitHubRepoParc(val name: String, val htmlUrl: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(htmlUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitHubRepoParc> {
        override fun createFromParcel(parcel: Parcel): GitHubRepoParc {
            return GitHubRepoParc(parcel)
        }

        override fun newArray(size: Int): Array<GitHubRepoParc?> {
            return arrayOfNulls(size)
        }
    }
}