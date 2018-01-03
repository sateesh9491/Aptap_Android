package com.app.aptap.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.app.aptap.util.Constants;

public class SmsReceiver extends BroadcastReceiver {
    public static String TAG = SmsReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "SmsReceiver:" + intent);
        Bundle data  = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessage.getDisplayOriginatingAddress();
            //You must check here if the sender is your provider and not another one with same text.
            String messageBody = smsMessage.getMessageBody();
            Intent smsIntent = new Intent("otp");
            smsIntent.putExtra("message",messageBody.substring(0, Constants.OTP_LENGTH));
            LocalBroadcastManager.getInstance(context).sendBroadcast(smsIntent);
        }
    }
}
