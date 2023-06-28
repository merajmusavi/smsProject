package com.example.smsproject

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.smsproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      val myReceiver = MyReceiver()
       registerReceiver(myReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        binding.button.setOnClickListener {
            if (requestPermission()) {
                val smsPending = PendingIntent.getBroadcast(this,0,Intent("SMS_P"),0)
                registerReceiver(SMSReceiver(), IntentFilter("SMS_P"))
                val getReceiver = PendingIntent.getBroadcast(this,0,Intent("SMS_R"),0)
                registerReceiver(GetReciver(), IntentFilter("SMS_R"))

                val sms = SmsManager.getDefault()

                sms.sendTextMessage("+989362580601", null, "hello its meraj", smsPending, getReceiver)

            }
        }

        registerReceiver(ReadSms(), IntentFilter("android.provider.Telephony.SMS_RECEIVED"))


    }

    fun requestPermission(): Boolean {
        val requestWeNeed = mutableListOf<String>()
        val readSms = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_SMS
        )
        if (readSms != PackageManager.PERMISSION_GRANTED) {
            requestWeNeed.add(android.Manifest.permission.READ_SMS)

        }

        val receive = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.RECEIVE_SMS
        )
        if (receive != PackageManager.PERMISSION_GRANTED) {
            requestWeNeed.add(android.Manifest.permission.RECEIVE_SMS)

        }

        var sendSms = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.SEND_SMS
        )
        if (sendSms != PackageManager.PERMISSION_GRANTED) {
            requestWeNeed.add(android.Manifest.permission.SEND_SMS)
        }

        if (requestWeNeed.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, requestWeNeed.toTypedArray(), 0)
            return false
        }

        return true
    }

}