package com.example.wehelpyoubook.scrapingdata

import android.util.Log
import com.example.wehelpyoubook.model.*
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.wehelpyoubook.vouchercontroller.VoucherDatasource
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
import com.example.wehelpyoubook.vouchercontroller.VoucherDatasource
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.io.IOException
val db = Firebase.firestore
<<<<<<< HEAD
<<<<<<< HEAD

private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
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

<<<<<<< HEAD
<<<<<<< HEAD
    suspend fun restaurantScraping() =
=======
    suspend fun restaurantScraping(url: String) =
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
    suspend fun restaurantScraping() =
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
        runBlocking {
            async {
                for (page: Int in 1..5) {
                    try {

<<<<<<< HEAD
<<<<<<< HEAD
                        val tmpUrl = linkServer + page.toString()
=======
                        val tmpUrl = url + page.toString()
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
                        val tmpUrl = linkServer + page.toString()
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
                        val doc = Jsoup.connect(tmpUrl)
                        val event = doc.get().getElementsByClass("row-item filter-result-item")

                        for (i in event) {
                            val resImage = i.getElementsByTag("img").attr("src")
                            val resId = getID(resImage)
                            val resTitle = i.getElementsByTag("img").attr("alt")
                            val resRate = i.getElementsByClass("point highlight-text").text()
                            val resAddress = i.getElementsByClass("address").text()
<<<<<<< HEAD

=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
                            val tmpRes = Restaurant(
                                resId,
                                resTitle,
                                resRate,
                                resAddress,
                                resImage
                            )
                            resList.add(tmpRes)
<<<<<<< HEAD
=======

>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
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
<<<<<<< HEAD
                            VoucherDatasource().UpVoucherData("",resId)
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                resList
            }
        }.await()

<<<<<<< HEAD
<<<<<<< HEAD
    suspend fun reviewScraping() =
=======
    suspend fun reviewScraping(url: String) =
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
    suspend fun reviewScraping() =
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
        runBlocking {

            async {
                for (page: Int in 1..5) {
                    try {
<<<<<<< HEAD
<<<<<<< HEAD
                        val tmpUrl = linkServer + page.toString()
=======
                        val tmpUrl = url + page.toString()
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
                        val tmpUrl = linkServer + page.toString()
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
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
<<<<<<< HEAD
                                            "",
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
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
                                    VoucherDatasource().UpVoucherData(userID)
                                }
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }.await()
<<<<<<< HEAD
<<<<<<< HEAD
    suspend fun foodScraping() =
=======
    suspend fun foodScraping(url: String) =
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
    suspend fun foodScraping() =
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
        runBlocking {
            async {
                for (page: Int in 1..5) {
                    try {

<<<<<<< HEAD
<<<<<<< HEAD
                        val tmpUrl = linkServer + page.toString()
=======
                        val tmpUrl = url + page.toString()
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
                        val tmpUrl = linkServer + page.toString()
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
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



