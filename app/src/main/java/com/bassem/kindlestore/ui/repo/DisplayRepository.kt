package com.bassem.kindlestore.ui.repo

import android.app.Activity
import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri



class DisplayRepository {


    fun downloadBook(activity: Activity, link: String, filename: String) {
        val request =
            DownloadManager.Request(Uri.parse("https://drive.google.com/uc?export=download&id=$link"))
                .setTitle(
                    "$filename - تم التحميل من كيندل ستور"
                )
                .setDescription("كيندل استور ابلكييشن مجاني متوفر عليه روايات وكتب بصيغة الكيندل")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
        val dm = activity.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)

    }




}