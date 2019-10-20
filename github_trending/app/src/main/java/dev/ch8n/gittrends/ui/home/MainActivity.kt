package dev.ch8n.gittrends.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import dev.ch8n.gittrends.R
import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.ui.base.BaseActivity
import dev.ch8n.gittrends.ui.base.adapters.AdapaterEvents
import dev.ch8n.gittrends.ui.base.adapters.TrendingListAdapter
import dev.ch8n.gittrends.ui.gitPreview.PREVIEW_URL
import dev.ch8n.gittrends.ui.gitPreview.PreviewActivity
import dev.ch8n.gittrends.ui.home.dialogs.GitProfileBottomSheet
import dev.ch8n.gittrends.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

//Please find test in test not in android test
//I have implemented AndroidX testing espresso + roboelectric Jvm test
class MainActivity : BaseActivity(), GitProfileBottomSheet.GitBottomSheetListener {

    override val contentView: Int
        get() = R.layout.activity_main

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var connectionProvider: ConnectionProvider

    lateinit var trendingListAdapter: TrendingListAdapter
    private var bottomSheet: GitProfileBottomSheet? = null

    override fun setup() {

        list_github_trending.run {
            adapter = TrendingListAdapter.newInstance()
                .also {
                    trendingListAdapter = it
                }
        }

        trendingListAdapter.onEvent().observe(this, Observer { event ->
            when (event) {
                is AdapaterEvents.OnClickLearnMore -> onClickOfTrendingList(event)
            }
        })

        connectionProvider.networkStatus.observe(this, Observer  {
            isConnected->
            if (isConnected){
                networkConnected()
            }else{
                networkDisconnected()
            }
        })

        getTrendRepoEvent()

        swipe_refresh.setOnRefreshListener {
            getTrendRepoEvent()
        }
    }

    private fun networkDisconnected() {
        "disconnected".toToast(this)
    }

    private fun networkConnected() {
        "connected".toToast(this)
    }

    private fun getTrendRepoEvent() {
        swipe_refresh.isRefreshing = true
        viewModel.getTrendingProjects("java")
            .observe(this, Observer { result ->
                swipe_refresh.isRefreshing = false
                when (result) {
                    is Result.Success -> onSuccessTrendingInfo(result)
                    is Result.Error -> onError(result)
                }
            })
    }

    private fun onClickOfTrendingList(event: AdapaterEvents.OnClickLearnMore) {
        val item = trendingListAdapter.getItemAt(event.position)
        if (bottomSheet?.isVisible == true) {
            bottomSheet?.dismiss()
        }
        bottomSheet = GitProfileBottomSheet.newInstance(item)
        bottomSheet?.show(supportFragmentManager, "btm_profile")
    }

    private fun onError(error: Result.Error<Exception>) {
        "Something went wrong!".toToast(this)
        error.error.localizedMessage.logError()
    }

    private fun onSuccessTrendingInfo(result: Result.Success<List<TrendingItem>>) =
        trendingListAdapter.submitList(result.value)

    override fun onClickPreviewProject(url: String) {
        if (bottomSheet?.isVisible == true) {
            bottomSheet?.dismiss()
        }
        launchActivity<PreviewActivity>(Bundle().also {
            it.putString(PREVIEW_URL, url)
        })
    }

    override fun onClickPreviewProfile(url: String) = onClickPreviewProject(url)

}
