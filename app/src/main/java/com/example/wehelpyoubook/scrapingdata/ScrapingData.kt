package com.example.wehelpyoubook.scrapingdata

import com.example.wehelpyoubook.model.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.io.IOException

private  const val mainUrl : String = "https://www.foody.vn/"
private const val TAG = "MyActivity"
class ScrapingData {
    var resList = ArrayList<Restaurant>()
    var reviewList = ArrayList<Review>()
    var userList = ArrayList<User>()
    var foodList = ArrayList<Food>()
    private fun getID(src : String) :String{
        val id  = src.split("/")
        return id[5]
    }
    private fun getAccount(src : String): String{
        val account  = src.split("/")
        return account[2]
    }

    suspend fun restaurantScraping(url: String) =
        runBlocking {
            async {
                for (page: Int in 1..5) {
                    try {

                        val tmpUrl = url + page.toString()
                        val doc = Jsoup.connect(tmpUrl)
                        val event = doc.get().getElementsByClass("row-item filter-result-item")

                        for (i in event) {
                            val resImage = i.getElementsByTag("img").attr("src")
                            val resId = getID(resImage)
                            val resTitle = i.getElementsByTag("img").attr("alt")
                            val resRate = i.getElementsByClass("point highlight-text").text()
                            val resAddress = i.getElementsByClass("address").text()
                            resList.add(
                                Restaurant(resId,
                                resTitle,
                                resRate,
                                resAddress,
                                resImage)
                            )
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                resList
            }
        }.await()

    suspend fun reviewScraping(url: String) =
        runBlocking {

            async {
                for (page: Int in 1..5) {
                    try {
                        val tmpUrl = url + page.toString()
                        val doc = Jsoup.connect(tmpUrl)
                        val resEvents = doc.get().getElementsByClass("row-item filter-result-item")

                        for(r in resEvents) {
                            val items = r.getElementsByClass("row-view-reviews")
                            for (i in items) {
                                val reviews = i.getElementsByClass("items")
                                for (review in reviews) {
                                    val userImage = review.getElementsByTag("img").attr("src")
                                    val account =
                                        getAccount(review.getElementsByTag("a").attr("href"))
                                    val comment = review.getElementsByTag("span").text()
                                    val name = review.getElementsByTag("b")
                                        .tagName("data-bind").text()
                                    val userID = getID(userImage)
                                    val resId = getID(
                                        i.getElementsByTag("img").attr("src")
                                    )
                                    userList.add(User(
                                        userID,
                                        userImage,
                                        name,
                                        "customer",
                                        "",
                                        account,
                                        account,
                                        "",
                                        mutableSetOf<Restaurant>()
                                        )
                                    )
                                    reviewList.add(
                                        Review(resId,
                                        userID,
                                        comment)
                                    )
                                }
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                resList
            }
        }.await()
    suspend fun foodScraping(url: String) =
        runBlocking {
            async {
                for (page: Int in 1..5) {
                    try {

                        val tmpUrl = url + page.toString()
                        val doc = Jsoup.connect(tmpUrl)
                        val event = doc.get().getElementsByClass("row-item filter-result-item")

                        for (i in event) {
                            val resId = getID(
                                i.getElementsByTag("img").attr("src")
                            )

                            val resUrl = mainUrl + i.getElementsByTag("a").attr("href")
                            // Get data of food
                            val focusFoodUrl = resUrl + "/album-mon-an"
                            val foodDoc = Jsoup.connect(focusFoodUrl)

                            val foodEvent = foodDoc.get().getElementsByClass("thumb-image")
                            for (food in foodEvent){
                                val imageUrl = food.getElementsByTag("a").attr("href")
                                val name = ""
                                val price = 0
                                foodList.add(
                                    Food(
                                        resId,
                                        name,
                                        price,
                                        imageUrl
                                    )
                                )
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                resList
            }
        }.await()
}



