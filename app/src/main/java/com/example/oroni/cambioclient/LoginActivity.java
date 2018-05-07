package com.example.oroni.cambioclient;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.oroni.cambioclient.URLS.URLs;
import com.example.oroni.cambioclient.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn;
    EditText editemail,editpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editemail=findViewById(R.id.editemail);
        editpass=findViewById(R.id.editpassword);
        loginbtn=findViewById(R.id.btnlogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Login();
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            }
        });
    }

    private void Login() {
//
//        if(editemail.getText().toString().equals("admin@gmail.com") &&
//        editpass.getText().toString().equals("admin")) {
//            Toast.makeText(getApplicationContext(),
//                    "Redirecting...",Toast.LENGTH_SHORT).show();
//
//            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(intent);
//        }else{
//            Toast.makeText(getApplicationContext(), "WrongCredentials",Toast.LENGTH_SHORT).show();
//
//        }



        final String email = editemail.getText().toString();
        final String password = editpass.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(email)) {
            editemail.setError("Please enter your email address");
            editemail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editpass.setError("Please enter your password");
            editpass.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("email"),
                                        userJson.getString("password")
                                );

                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", email);
                params.put("password", password);
                return params;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
