package dev.ch8n.gittrends.utils

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Response
import java.io.File


class ConnectionManger(
    private val connectivityManager: ConnectivityManager,
    private val cacheDirPath: String
) : ConnectionProvider {

    private val connectStatus = MutableLiveData<Boolean>(false)
    private val cachedStatus = MutableLiveData<Boolean>(false)

    override fun getConnectionInterceptor() = Interceptor { chain ->
        val isConnected: Boolean = getNetworkStatus()
        connectStatus.postValue(isConnected)
        when (isConnected) {
            true -> chain.proceed(chain.request())
            false -> getCacheResponse(chain)
        }
    }

    private fun getNetworkStatus() = connectivityManager.activeNetworkInfo?.isConnected == true

    private fun getCacheResponse(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header(CACHE_CONTROL, CACHE_TYPE_POLICY)
            .build()
        val response = chain.proceed(request)
        cachedStatus.postValue(response.cacheResponse()==null)
        if (response.cacheResponse()==null){
            "Cache for API not available".logError("Cache Network")
        }
        return response
    }

    override var networkStatus = connectStatus as LiveData<Boolean>
        private set

    override var isCacheValid = cachedStatus as LiveData<Boolean>
        private set


    override fun getDiskCache(): Cache {
        val cacheDir = File(cacheDirPath, "cache")
        return Cache(cacheDir, DISK_CACHE_SIZE)
    }
}

interface ConnectionProvider {
    fun getConnectionInterceptor(): Interceptor
    fun getDiskCache(): Cache
    val networkStatus: LiveData<Boolean>
    val isCacheValid : LiveData<Boolean>
}

const val DISK_CACHE_SIZE: Long = 10 * 1024 * 1024 // 10MB
const val CACHE_VALID_TIME: Long = 60 * 60 * 24 // 24 hours

const val CACHE_CONTROL = "Cache-Control"
const val CACHE_TYPE_POLICY = "public, only-if-cached, max-stale=$CACHE_VALID_TIME"
