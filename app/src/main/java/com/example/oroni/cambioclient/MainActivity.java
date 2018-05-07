package com.example.oroni.cambioclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oroni.cambioclient.URLS.URLs;
import com.example.oroni.cambioclient.model.Orientation;
import com.example.oroni.cambioclient.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_ORIENTATION = "EXTRA_ORIENTATION";
    public final static String EXTRA_WITH_LINE_PADDING = "EXTRA_WITH_LINE_PADDING";

    @BindView(R.id.verticalTimeLineButton)
    Button mVerticalTimeLineButton;
    @BindView(R.id.btnchecktrack)
    Button btnchecktrack;

    @BindView(R.id.idview)
    TextView idview;
    @BindView(R.id.getordertracker)
    EditText getordertracker;


    String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnchecktrack.setVisibility(View.GONE);
        mVerticalTimeLineButton.setVisibility(View.GONE);


        getordertracker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnchecktrack.setVisibility(View.GONE);
                mVerticalTimeLineButton.setVisibility(View.GONE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnchecktrack.setVisibility(View.VISIBLE);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
//        idview.setText(String.valueOf(user.getCustomer_id()));

        mVerticalTimeLineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(Orientation.VERTICAL, false);
            }
        });


        btnchecktrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrder();

            }
        });



    }


    private void getOrder(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String ordertracker = getordertracker.getText().toString();

        String uri = String
                .format(URLs.URL_ORDER+"%1$s",
                        ordertracker);


        if (ordertracker != null && !ordertracker.equals("")) {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {

            // disable cache
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                btnchecktrack.setEnabled(false);
                mVerticalTimeLineButton.setVisibility(View.VISIBLE);
                getordertracker.setEnabled(false);


                try {

                    JSONObject obj = new JSONObject(response);

                    JSONArray keys = obj.names ();

                    for (int i = 0; i < keys.length (); ++i) {

                        String longitude = obj.getString(("longitude"));
                        String latitude = obj.getString("latitude");
                        idview.setText(" Your Order Contains: \n" +longitude+ "\n"+latitude);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                        Log.d("Response", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("pk", ordertracker); //Add the data you'd like to send to the server.
                param.put("username","username");
                param.put("password","password");

                return param;
            }
        };

            stringRequest.setShouldCache(false);
            queue.add(stringRequest);


        } }

    private void onButtonClick(Orientation orientation, boolean withLinePadding) {
        Intent intent = new Intent(this, TimelineActivity.class);
        intent.putExtra(EXTRA_ORIENTATION, orientation);
        intent.putExtra(EXTRA_WITH_LINE_PADDING, withLinePadding);
        startActivity(intent);
    }



}
