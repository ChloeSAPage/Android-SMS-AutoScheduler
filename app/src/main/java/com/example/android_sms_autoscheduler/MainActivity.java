package com.example.android_sms_autoscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText message;
    Button send;

    private static final int SMS_PERMISSION_CODE = 100;
    private static final int CONTACTS_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContacts();

        send = findViewById(R.id.button);
//        phonenumber = findViewById(R.id.editText);
        message = findViewById(R.id.editText2);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION_CODE);
        } else {
            getContacts();
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String number = phonenumber.getText().toString().trim();
                String msg = message.getText().toString().trim();

//                if (number.isEmpty() || msg.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this,
//                            new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
//                } else {
//                    sendSms(number, msg);
//                }

                // display toast
                Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getContacts() {
        ListView listView = findViewById(R.id.contactListView);

        ArrayList<String> dummyContacts = new ArrayList<>();
        dummyContacts.add("Timmy - 12345");
        dummyContacts.add("Timmy2 - 123456");
        dummyContacts.add("Timmy3 - 123457");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dummyContacts
        );

        listView.setAdapter(adapter);
    }


    private void sendSms(String number, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS Permission Granted. Try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == CONTACTS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Contacts Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Contacts Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
