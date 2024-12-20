package com.example.lab5_bai1_bson_22521247;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver smsReceiver;
    private TextView txtShowMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShowMess = findViewById(R.id.tv_content);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
                1);


        registerSmsReceiver();

    }

    private void registerSmsReceiver() {
        // Tạo BroadcastReceiver để xử lý tin nhắn SMS
        smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Hiển thị thông báo hoặc xử lý logic tại đây
                Toast.makeText(context, "Bạn có tin nhắn mới!", Toast.LENGTH_LONG).show();
                Bundle bundle = intent.getExtras();
                Object[] pdus = (Object[]) bundle.get("pdus");
                for (Object pdu : pdus) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = message.getDisplayOriginatingAddress();
                    String content = message.getMessageBody();

                    txtShowMess.setText("SMS từ: " + sender + "\nNội dung: " + content);
                }

            }
        };

        // Đăng ký BroadcastReceiver với IntentFilter
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy đăng ký BroadcastReceiver khi Activity bị hủy
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
    }


}
