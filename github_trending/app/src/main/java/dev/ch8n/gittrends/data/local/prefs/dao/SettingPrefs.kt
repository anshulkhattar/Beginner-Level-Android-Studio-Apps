package dev.ch8n.gittrends.data.local.prefs.dao

import android.content.Context
import dev.ch8n.gittrends.data.local.prefs.PreferenceProvider
import dev.ch8n.gittrends.data.local.prefs.config.PrefsConfig
import dev.ch8n.gittrends.utils.get
import dev.ch8n.gittrends.utils.put


const val APP_PREF_FILE_NAME = "dev.ch8n.gittrends.app_prefs"
const val KEY_GITHUB_LOGINED = "$APP_PREF_FILE_NAME.is_github_login"
const val KEY_LAST_SYNC = "$APP_PREF_FILE_NAME.last_sync"
const val KEY_AUTH_TOKEN = "$APP_PREF_FILE_NAME.auth_token"
const val KEY_AUTH_EMAIL = "$APP_PREF_FILE_NAME.auth_email"
const val KEY_AUTH_PASSWORD = "$APP_PREF_FILE_NAME.auth_password"

class SettingPrefs(context: Context, fileName: String = APP_PREF_FILE_NAME) :
    PrefsConfig(context, fileName),
    PreferenceProvider.Auth {

    override var authToken: String
        get() = prefs.get(KEY_AUTH_TOKEN, "")
        set(value) = prefs.put(KEY_AUTH_TOKEN, value)

    override var isGithubLogin: Boolean
        get() = prefs.get(KEY_GITHUB_LOGINED, false)
        set(value) = prefs.put(KEY_GITHUB_LOGINED, value)

    override var lastSync: Long
        get() = prefs.get(KEY_LAST_SYNC, 0)
        set(value) = prefs.put(KEY_LAST_SYNC, value)

    override var authEmail: String
        get() = prefs.get(KEY_AUTH_EMAIL, "")
        set(value) = prefs.put(KEY_AUTH_EMAIL, value)

    override var password: String
        get() = prefs.get(KEY_AUTH_PASSWORD, "")
        set(value) = prefs.put(KEY_AUTH_PASSWORD, value)
}