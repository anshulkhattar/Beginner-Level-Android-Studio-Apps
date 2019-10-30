package dev.ch8n.gittrends.data.local.prefs

import android.content.Context
import dev.ch8n.gittrends.data.local.prefs.dao.SettingPrefs
import dev.ch8n.gittrends.data.local.prefs.dao.UserPrefs

class AppPrefs(private val appContext: Context) : PreferenceProvider {

    override val auth: PreferenceProvider.Auth
            by lazy { SettingPrefs(context = appContext) }

    override val user: PreferenceProvider.User
            by lazy { UserPrefs(context = appContext) }


}