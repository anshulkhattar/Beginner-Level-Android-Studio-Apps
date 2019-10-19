package dev.ch8n.gittrends.ui.gitPreview

import android.webkit.WebView
import android.webkit.WebViewClient
import dev.ch8n.gittrends.R
import dev.ch8n.gittrends.ui.base.BaseActivity
import dev.ch8n.gittrends.utils.logError
import dev.ch8n.gittrends.utils.setVisibility
import dev.ch8n.gittrends.utils.toToast
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : BaseActivity() {
    override val contentView: Int
        get() = R.layout.activity_preview

    override fun setup() {
        val previewUrl = intent.extras?.getString(PREVIEW_URL)
        if (previewUrl == null) {
            "Something went wrong".toToast(this)
            "Intent null for imageUrl".logError()
            finish()
        }

        fab_preview_back.setOnClickListener { finish() }
        progress_view.setVisibility(true)


        preview_webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress_view.setVisibility(false)
            }
        }

        preview_webview.loadUrl(previewUrl)

    }

}

const val PREVIEW_URL = "PREVIEW_URL"
