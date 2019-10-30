package dev.ch8n.gittrends.data.local.prefs

interface PreferenceProvider {

    interface Auth{
        var isGithubLogin:Boolean
        var lastSync:Long
        var authToken :String
        var authEmail :String
        var password :String
    }

    interface User{
        var userName:String
        var primaryEmail:String
        var githubEmail:String
    }

    val auth : PreferenceProvider.Auth
    val user: PreferenceProvider.User

}