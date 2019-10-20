package dev.ch8n.gittrends.di.modules

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dev.ch8n.gittrends.GitTrendApp
import dev.ch8n.gittrends.data.remote.config.ApiManager
import dev.ch8n.gittrends.data.remote.config.BaseUrl
import dev.ch8n.gittrends.data.remote.config.NETWORK_TIMEOUT
import dev.ch8n.gittrends.data.remote.config.SecureConnectionProvider
import dev.ch8n.gittrends.utils.ConnectionManger
import dev.ch8n.gittrends.utils.ConnectionProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkBinder {


    @Provides
    @Singleton
    fun provideConnectionManager(app:GitTrendApp):ConnectionProvider {
        val connectivityManager = requireNotNull(
            app.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        )
        val cacheDir = app.cacheDir.path
        return ConnectionManger(connectivityManager,cacheDir)
    }

    @Provides
    fun provideOkHttpClient(connectionProvider: ConnectionProvider): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(connectionProvider.getConnectionInterceptor())
        .cache(connectionProvider.getDiskCache())
        .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiManager(retrofit: Retrofit): ApiManager = ApiManager(retrofit)


}