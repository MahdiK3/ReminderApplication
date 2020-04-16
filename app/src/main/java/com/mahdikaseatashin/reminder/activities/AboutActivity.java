package com.mahdikaseatashin.reminder.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mahdikaseatashin.reminder.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AboutActivity extends AppCompatActivity {

    private int CALL_PHONE_CODE = 1;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.nav_drawer_toolbar_2);
        toolbar.setTitle(R.string.about);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void telegram(View view) {
        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/Mahdi_K3F"));
        startActivity(telegram);
    }

    public void instagram(View view) {
        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/invites/contact/?i=8gizn6rgx468&utm_content=1089tai"));
        startActivity(telegram);
    }

    public void callMe(View view) {
        Log.e("My_Log", "HERE WE ARE");

        if (ContextCompat.checkSelfPermission(AboutActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse("tel: +989025648561");
            Intent i = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(i);
        } else {
            requestCallPhonePermission();
        }
    }

    private void requestCallPhonePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_CODE);
    }

    public void email(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mahdikaseatashin@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Scheduler Application");
        i.putExtra(Intent.EXTRA_TEXT, "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AboutActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
