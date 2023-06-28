package com.example.smsproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast

class ReadSms : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val smsMessage = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        var smsBody = ""
        for (sms in smsMessage){
            smsBody = sms.displayMessageBody
        }
        Toast.makeText(context, smsBody, Toast.LENGTH_SHORT).show()
    }
}