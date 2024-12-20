package com.example.bai3demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.telephony.SmsManager;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String message = smsMessage.getDisplayMessageBody();

                    if (message != null && message.toLowerCase().contains("are you ok?")) {
                        // Hiển thị thông báo nhận được tin nhắn
                        Toast.makeText(context, "Received SMS from: " + sender, Toast.LENGTH_SHORT).show();

                        // Kiểm tra trạng thái Auto response
                        SharedPreferences sharedPreferences = context.getSharedPreferences("AutoResponsePrefs", Context.MODE_PRIVATE);
                        boolean isAutoResponseEnabled = sharedPreferences.getBoolean("auto_response", false);

                        if (isAutoResponseEnabled) {
                            // Gửi tin nhắn tự động
                            sendAutoResponse(context, sender);
                        } else {
                            // Mở MainActivity để người dùng phản hồi
                            Intent mainIntent = new Intent(context, MainActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mainIntent.putExtra("sender", sender);
                            context.startActivity(mainIntent);
                        }
                    }
                }
            }
        }
    }

    // Hàm gửi tin nhắn tự động
    private void sendAutoResponse(Context context, String recipient) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            String autoResponseMessage = "I am fine and safe. Worry not!";
            smsManager.sendTextMessage(recipient, null, autoResponseMessage, null, null);

            Toast.makeText(context, "Auto-response sent to: " + recipient, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to send auto-response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}







