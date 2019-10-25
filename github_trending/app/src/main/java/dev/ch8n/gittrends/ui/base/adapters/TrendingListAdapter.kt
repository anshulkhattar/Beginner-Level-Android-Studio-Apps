package dev.ch8n.gittrends.ui.base.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.ch8n.gittrends.R
import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.ui.home.listViewHolder.TrendingViewHolder


class TrendingListAdapter private constructor(diffUtil: DiffUtil.ItemCallback<TrendingItem>) :

    ListAdapter<TrendingItem, TrendingViewHolder>(diffUtil) {

    private val listEvent = MutableLiveData<AdapaterEvents>()

    fun onEvent(): LiveData<AdapaterEvents> = listEvent

    fun getItemAt(position: Int) = getItem(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val holderView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_trending, parent, false)
        return TrendingViewHolder(holderView)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.onBind(getItem(position), listEvent)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrendingItem>() {

            override fun areItemsTheSame(oldItem: TrendingItem, newItem: TrendingItem): Boolean =
                oldItem.gitProfileName == newItem.gitProfileName

            override fun areContentsTheSame(oldItem: TrendingItem, newItem: TrendingItem): Boolean =
                oldItem.avatar == newItem.avatar &&
                        oldItem.gitProfileName == newItem.gitProfileName &&
                        oldItem.gitProfileUrl == newItem.gitProfileUrl &&
                        oldItem.username == newItem.username &&
                        oldItem.projectDesc == newItem.projectDesc &&
                        oldItem.projectName == newItem.projectName &&
                        oldItem.projectUrl == newItem.projectUrl
        }

        fun newInstance() = TrendingListAdapter(DIFF_CALLBACK)
    }

}

sealed class AdapaterEvents {
    data class OnClickLearnMore(val position: Int) : AdapaterEvents()
}



