package com.example.oroni.cambioclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceRatingActivity extends AppCompatActivity {
    RatingBar mRatingBar;
    TextView ratingview;
    EditText mFeedback;
    Button btnfeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_rating);

        ratingview=findViewById(R.id.viewrating);
        mFeedback=findViewById(R.id.mFeedback);



        mRatingBar =  findViewById(R.id.ratingBar);

        btnfeedback=findViewById(R.id.btnfeedback);

        btnfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(getApplicationContext(), "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingview.setText(String.valueOf(v));


            }
        });
    }
}
