package dev.ch8n.gittrends.ui.home.listViewHolder

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.hadilq.liveevent.LiveEvent
import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.ui.base.adapters.AdapaterEvents
import kotlinx.android.synthetic.main.item_list_trending.view.*


class TrendingViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun onBind(
        item: TrendingItem,
        listEvent: MutableLiveData<AdapaterEvents>
    ) = with(view) {

        chip_project_url.setOnClickListener {
            listEvent.postValue(AdapaterEvents.OnClickLearnMore(adapterPosition))
        }

        text_project_desc.text = item.projectDesc
        text_project_name.text = item.projectName
    }

}
