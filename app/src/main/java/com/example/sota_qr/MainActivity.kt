package com.example.sota_qr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sota_qr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webview = binding.webview

        webview.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                CookieManager.getInstance().flush()
            }
        }
        val settings= webview.settings

        settings.javaScriptEnabled= true
        settings.setSupportMultipleWindows(false)
        settings.javaScriptCanOpenWindowsAutomatically= true
        settings.loadWithOverviewMode= true
        settings.loadWithOverviewMode= true
        settings.useWideViewPort = true
        settings.setSupportZoom(false)
        settings.layoutAlgorithm= WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        settings.cacheMode=WebSettings.LOAD_NO_CACHE
        settings.domStorageEnabled= true

        webview.loadUrl("https://qrcodethumb-phinf.pstatic.net/20220322_274/1647934068829ySySp_PNG/0W8yy.png")
        test()
    }

    fun test(){
        val imageFetcher = QRImageFetcher(context = this)
        imageFetcher.fetch()
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}
