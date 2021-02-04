package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        displayNotification("Simple WorkManager","Work Sudah Selesai")
        return Result.success()
    }

    private fun displayNotification(task:String, desc:String){

        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "simpleWorkManager",
                "simpleWorkManager",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
            val builder: NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, "simplifiedcoding")
                    .setContentTitle(task)
                    .setContentText(desc)
                    .setSmallIcon(R.mipmap.ic_launcher)

            manager.notify(1, builder.build())
    }
}