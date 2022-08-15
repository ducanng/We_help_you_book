package com.example.wehelpyoubook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.databinding.ActivityCustomerBinding
import com.example.wehelpyoubook.databinding.ActivityMainBinding
import com.example.wehelpyoubook.model.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ActivityCustomerBinding
    private var userId = ""
    private var manager : User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //user
        val user = Firebase.auth.currentUser
        if (user == null) {
            startActivity(Intent(this, HomeSignInActivity::class.java))
            finish()
        }
        val auth = Firebase.auth.currentUser
        userId = auth!!.uid

        val resDoc = com.example.wehelpyoubook.scrapingdata.db.collection("Users")
            .whereEqualTo("id",userId)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            println(manager.role )
            if (documentSnapshot.toObjects<User>().isNotEmpty()){
                manager = documentSnapshot.toObjects<User>()[0]
                if(manager.role == "manager"){
                    binding = ActivityMainBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
                    setSupportActionBar(toolbar)
                    val drawerLayout: DrawerLayout = binding.drawerLayout
                    val navView: NavigationView = binding.navView
                    val navController = findNavController(R.id.nav_host_fragment_content_main)
                    appBarConfiguration = AppBarConfiguration(
                        setOf(
                            R.id.nav_home, R.id.nav_my_booking
                        ), drawerLayout
                    )
                    setupActionBarWithNavController(navController, appBarConfiguration)
                    navView.setupWithNavController(navController)
                    val header = navView.getHeaderView(0)
                    val name = header.findViewById<TextView>(R.id.display_name_nav)
                    val email = header.findViewById<TextView>(R.id.email_nav)
                    name.text = auth.displayName
                    email.text = auth.email
                }
                else if(manager.role == "customer"){
                    binding2 = ActivityCustomerBinding.inflate(layoutInflater)
                    setContentView(binding2.root)
                    val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
                    setSupportActionBar(toolbar)
                    val drawerLayout: DrawerLayout = binding2.drawerLayoutCustomer
                    val navView: NavigationView = binding2.navViewCustomer
                    val navController = findNavController(R.id.nav_host_fragment_content_main)
                    appBarConfiguration = AppBarConfiguration(
                        setOf(
                            R.id.nav_home, R.id.nav_my_booking, R.id.nav_feedback,R.id.nav_logout
                        ), drawerLayout
                    )
                    setupActionBarWithNavController(navController, appBarConfiguration)
                    navView.setupWithNavController(navController)
                    val header = navView.getHeaderView(0)
                    val name = header.findViewById<TextView>(R.id.display_name_nav)
                    val email = header.findViewById<TextView>(R.id.email_nav)
                    name.text = auth.displayName
                    email.text = auth.email

                }
            }
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }

    @SuppressLint("NewApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)

        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        println("navController")
        println(navController)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}