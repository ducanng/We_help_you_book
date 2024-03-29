package com.example.wehelpyoubook.scrapingdata

import android.util.Log
import com.example.wehelpyoubook.model.*
import com.example.wehelpyoubook.vouchercontroller.VoucherDatasource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.io.IOException
val db = Firebase.firestore

private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
private  const val mainUrl : String = "https://www.foody.vn/"
private const val TAG = "MyActivity"
class ScrapingData {
    private var resList = ArrayList<Restaurant>()
    private fun getID(src : String) :String{
        val id  = src.split("/")
        return id[5]
    }
    private fun getAccount(src : String): String{
        val account  = src.split("/")
        return account[2]
    }

    suspend fun restaurantScraping() =
        runBlocking {
            async {
                for (page: Int in 1..5) {
                    try {

                        val tmpUrl = linkServer + page.toString()
                        val doc = Jsoup.connect(tmpUrl)
                        val event = doc.get().getElementsByClass("row-item filter-result-item")

                        for (i in event) {
                            val resImage = i.getElementsByTag("img").attr("src")
                            val resId = getID(resImage)
                            val resTitle = i.getElementsByTag("img").attr("alt")
                            val resRate = i.getElementsByClass("point highlight-text").text()
                            val resAddress = i.getElementsByClass("address").text()

                            val tmpRes = Restaurant(
                                resId,
                                resTitle,
                                resRate,
                                resAddress,
                                resImage
                            )
                            resList.add(tmpRes)
                            db.collection("Restaurants")
                                .add(
                                    tmpRes
                                )
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "Restaurant added with ID: ${documentReference.id}")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding restaurant", e)
                                }
                            VoucherDatasource().UpVoucherData("",resId)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                resList
            }
        }.await()

    suspend fun reviewScraping() =
        runBlocking {

            async {
                for (page: Int in 1..5) {
                    try {
                        val tmpUrl = linkServer + page.toString()
                        val doc = Jsoup.connect(tmpUrl)
                        val resEvents = doc.get().getElementsByClass("row-item filter-result-item")

                        for(r in resEvents) {
                            val items = r.getElementsByClass("row-view-reviews")
                            val resId = getID(
                                r.getElementsByTag("img").attr("src")
                            )
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
                                    db.collection("Users").add(
                                        User(
                                            userID,
                                            userImage,
                                            name,
                                            "customer",
                                            "",
                                            account,
                                            account,
                                            "",
                                            ""
                                        )
                                    )
                                        .addOnSuccessListener { documentReference ->
                                            Log.d(TAG, "User added with ID: ${documentReference.id}")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(TAG, "Error adding user", e)
                                        }
                                    db.collection("Reviews").add(
                                        Review(resId,
                                            userID,
                                            comment)
                                    )
                                        .addOnSuccessListener { documentReference ->
                                            Log.d(TAG, "Review added with ID: ${documentReference.id}")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(TAG, "Error adding review", e)
                                        }
                                }
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }.await()
    suspend fun foodScraping() =
        runBlocking {
            async {
                for (page: Int in 1..5) {
                    try {

                        val tmpUrl = linkServer + page.toString()
                        val doc = Jsoup.connect(tmpUrl)
                        val event = doc.get().getElementsByClass("row-item filter-result-item")

                        for (i in event) {
                            val resId = getID(
                                i.getElementsByTag("img").attr("src")
                            )

                            val resUrl = mainUrl + i.getElementsByTag("a").attr("href")
                            // Get data of food
                            val focusFoodUrl = "$resUrl/album-mon-an"
                            val foodDoc = Jsoup.connect(focusFoodUrl)

                            val foodEvent = foodDoc.get().getElementsByClass("thumb-image")
                            for (food in foodEvent){
                                val imageUrl = food.getElementsByTag("a").attr("href")
                                val name = ""
                                val price = 0
                                db.collection("Foods").add(
                                    Food(
                                        resId,
                                        name,
                                        price,
                                        imageUrl
                                    )
                                )
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(TAG, "Food added with ID: ${documentReference.id}")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(TAG, "Error adding food", e)
                                    }
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }.await()
}



