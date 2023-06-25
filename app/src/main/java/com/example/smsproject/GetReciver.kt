package com.example.smsproject

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class GetReciver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        var state = ""
        when (resultCode) {
            Activity.RESULT_OK -> {
                state = "با موفقیت دریافت شد"
            }

            Activity.RESULT_CANCELED -> {
                state = "در عملیات دریافت رکبی در کار است"
            }
        }
        Toast.makeText(context, state, Toast.LENGTH_SHORT).show()
    }

}