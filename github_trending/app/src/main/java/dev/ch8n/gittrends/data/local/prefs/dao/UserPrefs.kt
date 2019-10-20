package dev.ch8n.gittrends.data.local.prefs.dao

import android.content.Context
import dev.ch8n.gittrends.data.local.prefs.PreferenceProvider
import dev.ch8n.gittrends.data.local.prefs.config.PrefsConfig
import dev.ch8n.gittrends.utils.get
import dev.ch8n.gittrends.utils.put


const val USER_PREF_FILE_NAME = "dev.ch8n.gittrends.user_prefs"
const val KEY_USER_NAME = "$USER_PREF_FILE_NAME.user_name"
const val KEY_USER_EMAIL_PRIMARY = "$USER_PREF_FILE_NAME.user_email_primary"
const val KEY_USER_EMAIL_GITHUB = "$USER_PREF_FILE_NAME.user_email_github"


class UserPrefs(context: Context, fileName: String = USER_PREF_FILE_NAME) :
    PrefsConfig(context, fileName),
    PreferenceProvider.User {

    override var userName: String
        get() = prefs.get(KEY_USER_NAME, "UserName")
        set(value) = prefs.put(KEY_USER_NAME, value)

    override var primaryEmail: String
        get() = prefs.get(KEY_USER_EMAIL_PRIMARY, "Primary@email.com")
        set(value) = prefs.put(KEY_USER_EMAIL_PRIMARY, value)

    override var githubEmail: String
        get() = prefs.get(KEY_USER_EMAIL_GITHUB, "Github@email.com")
        set(value) = prefs.put(KEY_USER_EMAIL_GITHUB, value)

}