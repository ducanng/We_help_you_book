package com.example.wehelpyoubook.scrapingdata

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.wehelpyoubook.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

private const val TAG = "MyActivity"
class ScrapingData {
    var resList = ArrayList<Restaurant>()
    private fun getResID(src : String) :String{
        val resIdString  = src.split("/")
        return resIdString[5]
    }
    public fun restaurantScraping(url: String): ArrayList<Restaurant> {
        CoroutineScope(IO).launch {
            var resList = ArrayList<Restaurant>()
            var tmpUrl = url
            for(page : Int in 1..5) {
                try {
                    tmpUrl =tmpUrl + page.toString() + "/"

                    val doc = Jsoup.connect(tmpUrl)
                    val event = doc.get().getElementsByClass("row-item filter-result-item")


                    for (i in event) {
                        val resImage = i.getElementsByTag("img").attr("src")
                        val resId = getResID(resImage)
                        val resTitle = i.getElementsByTag("img").attr("alt")
                        val resRate = i.getElementsByClass("point highlight-text").text()
                        val resAddress = i.getElementsByClass("address").text()
                        resList.add(Restaurant(resId,resTitle,resRate,resAddress,resImage))
                    }
                    println(resList.size)
//            this@MainActivity.runOnUiThread( java.lang.Runnable {
//                Glide.with(this@MainActivity).load(imList[1]).into(im1)
//
//            })


                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return resList
    }
}