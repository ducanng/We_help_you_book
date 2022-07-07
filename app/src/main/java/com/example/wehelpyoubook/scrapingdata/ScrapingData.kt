package com.example.wehelpyoubook.scrapingdata

import com.example.wehelpyoubook.model.Restaurant
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import org.jsoup.Jsoup
import java.io.IOException

private const val TAG = "MyActivity"
class ScrapingData {
    var resList = ArrayList<Restaurant>()
    private fun getResID(src: String): String {
        val resIdString = src.split("/")
        return resIdString[5]
    }

    suspend fun restaurantScraping(url: String) =
        runBlocking {
            async {
                for(page : Int in 1..5) {
                    try {

                        val tmpUrl = url + page.toString()
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
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                resList
            }
        }.await()
}


