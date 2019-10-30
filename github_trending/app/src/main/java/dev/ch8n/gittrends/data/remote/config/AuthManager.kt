package dev.ch8n.gittrends.data.remote.config

import androidx.lifecycle.LiveData
import dev.ch8n.gittrends.data.local.prefs.PreferenceProvider
import dev.ch8n.gittrends.utils.ConnectionManger
import dev.ch8n.gittrends.utils.ConnectionProvider
import dev.ch8n.gittrends.utils.logError
import okhttp3.Cache
import okhttp3.Interceptor

data class AuthKeys(
    val accessToken: String,
    val apiKey: String,
    val userId: String
)

class AuthManager(
    private val authPrefs: PreferenceProvider.Auth
) : AuthProvider {


    override fun authorizedInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        // on unauthorized and on forbidden invalidate user
        if (response.code() == 401 || response.code() == 403) {
            // request or token again or logout user
            invalidate()
        }
        return@Interceptor response
    }

    override fun tokenRenewIntercepter(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        // if 'x-auth-token' is available into the response header
        // save the new token into session.The header key can be
        // different upon implementation of backend.
        val newToken = response.header("x-auth-token")
        if (newToken != null) {
            authPrefs.authToken = newToken
        }
        return@Interceptor response
    }

    // check on activity transition and API calls
    override fun isAuthorized(): Boolean = with(authPrefs) {
        return@with authToken.isNotBlank()
                && authEmail.isNotBlank()
                && password.isNotBlank()
    }

    override fun invalidate() {
        with(authPrefs) {
            authToken = ""
            authEmail = ""
            password = ""
            "user logged out...cleared authToken,email,password".logError("AuthManager")
        }
    }

}


interface AuthProvider {
    fun isAuthorized(): Boolean
    fun invalidate()
    fun tokenRenewIntercepter(): Interceptor
    fun authorizedInterceptor(): Interceptor
    //make a boolean flag which you can use as call back to what to do when user isUnAuth
}

class SecureConnectionManager(
    private val connectionManger: ConnectionProvider,
    private val authProvider: AuthProvider
) : SecureConnectionProvider {

    override fun tokenRenewIntercepter(): Interceptor = authProvider.tokenRenewIntercepter()

    override fun authorizedInterceptor(): Interceptor = authProvider.authorizedInterceptor()

    override fun connectionInterceptor(): Interceptor = connectionManger.getConnectionInterceptor()

    override fun diskCache(): Cache  = connectionManger.getDiskCache()
}

interface SecureConnectionProvider {
    fun tokenRenewIntercepter(): Interceptor
    fun authorizedInterceptor(): Interceptor
    fun connectionInterceptor(): Interceptor
    fun diskCache(): Cache
}