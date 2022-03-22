package com.example.sota_qr

import android.app.VoiceInteractor
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.jsoup.Jsoup

class QRImageFetcher(private val context: Context) {

    private val url = "https://qrcodethumb-phinf.pstatic.net/20220322_274/1647934068829ySySp_PNG/0W8yy.png"
    fun fetch(){

        val cookie = android.webkit.CookieManager.getInstance().getCookie(url)
        val queue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> {response ->
                print(response)
                print(response)
                val bitmap = parse(response)
                print(bitmap)
            },
            Response.ErrorListener {

            }){
            override fun getHeaders(): MutableMap<String, String> {

                val header: MutableMap<String, String> = HashMap()
                header["cookie"]= cookie
                return header
            }
        }

        queue.add(stringRequest)

    }
    fun parse(text:String): Bitmap?{
        val doc = Jsoup.parse(text)
        val image = doc.getElementById("qrImage")?: return null
        val src = image.attr("src")
        print(src)

        val prefix = "data:iamge/jpeg;base64, "
        val pureBase64 = src.replace(prefix, " ")
        val decodeByte: ByteArray = Base64.decode(pureBase64, Base64.DEFAULT)

        val bitmap= BitmapFactory.decodeByteArray(decodeByte, 0,decodeByte.size)

        return bitmap

    }

}