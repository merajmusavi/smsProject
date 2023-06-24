package com.example.smsproject

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.widget.Toast

class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var state = ""
        when (resultCode) {
            Activity.RESULT_OK -> {
                state = "پیام با موفقیت ارسال شد"
            }
            SmsManager.RESULT_ERROR_GENERIC_FAILURE -> {

                state = "generic"
            }
            SmsManager.RESULT_ERROR_NO_SERVICE -> {

state = "اپراتور در دسترس نیست"
            }
            SmsManager.RESULT_ERROR_RADIO_OFF -> {
                state = "سیم کارت در دسترسی نیست"
            }
        }
        Toast.makeText(context, state, Toast.LENGTH_SHORT).show()

    }
}