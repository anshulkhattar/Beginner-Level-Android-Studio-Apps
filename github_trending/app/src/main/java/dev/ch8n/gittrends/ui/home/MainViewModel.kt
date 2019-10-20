package dev.ch8n.gittrends.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ch8n.gittrends.data.others.repos.CacheTrendingRepo
import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.data.remote.repos.GithubRepo
import dev.ch8n.gittrends.utils.Result
import dev.ch8n.gittrends.utils.logError
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext



class MainViewModel(
    private val githubRepo: GithubRepo,
    private val cacheRepoistory: CacheTrendingRepo
) : ViewModel(),
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    fun getTrendingProjects(language: String): LiveData<Result<List<TrendingItem>, Exception>> {
        val result = MutableLiveData<Result<List<TrendingItem>, Exception>>()
        this.launch {

            // make network call
            val onRemoteInfo = async { Result.build { githubRepo.getTrendingProjects(language) } }

            // meanwhile show the local data
            val onLocalInfo = Result.build { cacheRepoistory.getTrendingItems() }
            when (onLocalInfo) {
                is Result.Error -> result.postValue(onLocalInfo)
                is Result.Success -> result.postValue(onLocalInfo.value)
            }

            //when remote data recivied
            val remoteResult = onRemoteInfo.await()

            // update with latest data
            result.postValue(remoteResult)

            //when onSuccess save data
            when (remoteResult) {
                is Result.Success -> {
                    val localUpdated =
                        Result.build { cacheRepoistory.putTrendingItems(remoteResult.value) }
                    when (localUpdated) {
                        is Result.Error -> localUpdated.error.localizedMessage.logError()
                    }
                }
            }


        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

}


