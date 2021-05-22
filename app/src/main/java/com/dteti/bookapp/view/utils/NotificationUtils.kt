package com.dteti.bookapp.view.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dteti.bookapp.R
import com.dteti.bookapp.view.ui.activities.MainActivity

private val requestCode = 123
private val dummiesTitle = listOf("Pick up your reading habit!", "Have time for some books?", "We Missed You")
private val dummiesMessage = listOf("Take a 5-minute reading", "Continue where we left off", "It's been a while since your reading")

fun NotificationManager.sendNotification(applicationContext : Context) {

    val randomIndex = (0..2).random()
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(applicationContext, requestCode, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val builder = NotificationCompat.Builder(applicationContext, applicationContext.getString(R.string.channelIdReminder))
            .setContentTitle(dummiesTitle[randomIndex])
            .setSmallIcon(R.drawable.ic_heart_pink)
            .setContentText(dummiesMessage[randomIndex])
            .setPriority(1) //2 means max
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    notify(requestCode, builder.build())
}

fun NotificationManager.cancelNotification() {
    cancelAll()
}

class NotifReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notifManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.d("Alarm", "Alarm")
        notifManager.cancelNotification()
        notifManager.sendNotification(context)
    }

}