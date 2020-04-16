package com.mahdikaseatashin.reminder.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mahdikaseatashin.reminder.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FeedbackActivity extends AppCompatActivity {

    TextView feedbackTextView;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Toolbar toolbar = findViewById(R.id.nav_drawer_toolbar_3);
        toolbar.setTitle(R.string.feedback);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void mailFeedback(View view) {
        feedbackTextView = findViewById(R.id.feedback_text);
        String textBody = feedbackTextView.getText().toString();
        if (textBody.equals("Please enter your feedback here") || textBody.isEmpty()) {
            Toast.makeText(getBaseContext(), "Please type your feedback in specified location!", Toast.LENGTH_SHORT).show();
        } else {
            Intent sendFeedback = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "mahdikaseatashin@gmail.com", null));
            sendFeedback.putExtra(Intent.EXTRA_SUBJECT, "Scheduler Feeback");
            sendFeedback.putExtra(Intent.EXTRA_TEXT, textBody);
            startActivity(Intent.createChooser(sendFeedback, "Send email..."));
            try {
                startActivity(Intent.createChooser(sendFeedback, "Send feedback..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(FeedbackActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
