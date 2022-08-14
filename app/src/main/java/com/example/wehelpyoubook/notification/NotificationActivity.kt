package com.example.wehelpyoubook.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.databinding.ActivityMainBinding
import com.example.wehelpyoubook.databinding.ActivityNotificationBinding
import com.example.wehelpyoubook.databinding.ActivityUpdateDataBinding
import com.example.wehelpyoubook.model.Orders
import com.example.wehelpyoubook.mybooking.db
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class NotificationActivity() : AppCompatActivity() {
    private val CHANNEL_ID = "1234"
    private val notificationId = 101
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        R.id.btn_Notify.apply {
            sendNotify()
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Bug012"
            val descriptionText = "Bug 0 and 1 and 2"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotify(){
        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("My notification")
            .setContentText("Booking successfully")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }

}