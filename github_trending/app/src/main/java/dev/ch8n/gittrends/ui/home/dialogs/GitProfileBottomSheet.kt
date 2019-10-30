package dev.ch8n.gittrends.ui.home.dialogs

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.ch8n.gittrends.R
import dev.ch8n.gittrends.data.model.local.list.TrendingItem
import dev.ch8n.gittrends.utils.loadAvatar
import dev.ch8n.gittrends.utils.toToast
import kotlinx.android.synthetic.main.btm_sheet_layout_user_info.*
import java.lang.IllegalStateException

class GitProfileBottomSheet : BottomSheetDialogFragment() {

    private var mGitBottomSheetListener: GitBottomSheetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.btm_sheet_layout_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val trendingItem = arguments?.getParcelable<TrendingItem>(ITEM)
            ?: throw IllegalStateException("null trendingItem object")
        updateDisplayProfile(trendingItem)
    }

    private fun updateDisplayProfile(item: TrendingItem) = with(item) {
        text_profile_name.text = gitProfileName
        chip_profile_url.text = username

        image_profile_avatar.loadAvatar(avatar)

        chip_profile_url.setOnClickListener {
            mGitBottomSheetListener?.onClickPreviewProfile(gitProfileUrl)
        }

        btn_preview_repo.setOnClickListener {
            mGitBottomSheetListener?.onClickPreviewProject(projectUrl)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent != null) {
            mGitBottomSheetListener = parent as GitBottomSheetListener
        } else {
            mGitBottomSheetListener = context as GitBottomSheetListener
        }
    }

    override fun onDetach() {
        mGitBottomSheetListener = null
        super.onDetach()
    }

    interface GitBottomSheetListener {
        fun onClickPreviewProject(url: String)
        fun onClickPreviewProfile(url: String)
    }

    companion object {
        fun newInstance(item: TrendingItem): GitProfileBottomSheet = GitProfileBottomSheet().apply {
            arguments = Bundle().apply {
                putParcelable(ITEM, item)
            }
        }
    }
}

const val ITEM = "TRENDING_ITEM"
