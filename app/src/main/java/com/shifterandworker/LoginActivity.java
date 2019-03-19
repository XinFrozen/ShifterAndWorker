package com.shifterandworker;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView forgotPass;
    private ProgressBar loading;
    private static String URL_LOGIN = "";
    RelativeLayout rlt;
    private EditText etUser, etPass;
    CheckBox chkBeniHatirla;
    Context context = this;


    ShrdPrferencs shrdPrferencs;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        manager = getFragmentManager();
        rlt = findViewById(R.id.rlt);
        loading = findViewById(R.id.loading);
        login = findViewById(R.id.login);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        forgotPass = findViewById(R.id.forgotPass);
        chkBeniHatirla = findViewById(R.id.chkBeniHatirla);

        AnimationDrawable animationDrawable = (AnimationDrawable) rlt.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        shrdPrferencs = new ShrdPrferencs();
        if (shrdPrferencs.getValueBoolean(context, "remember")) {//beni hatırla işaretlenmişse
            etUser.setText(shrdPrferencs.getValueString(context, "username"));
            chkBeniHatirla.setChecked(shrdPrferencs.getValueBoolean(context, "remember"));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = etUser.getText().toString().trim();
                String mPass = etPass.getText().toString().trim();
                if (chkBeniHatirla.isChecked()) {
                    shrdPrferencs.saveString(context, "username", etUser.getText().toString());
                } else {
                    shrdPrferencs.saveString(context, "username", "");
                }
                shrdPrferencs.saveBoolean(context, "remember", chkBeniHatirla.isChecked());
                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    Login(mEmail,mPass);
                } else {
                    etUser.setError("Lutfen Email adresinizi giriniz");
                    etPass.setError("Lutfen sifrenizi giriniz");
                }
            }
        });



       /* login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = etUser.getText().toString().trim();
                String mPass = etPass.getText().toString().trim();
                if (etUser.getText().toString().equals(getString(R.string.username)) &&
                        etPass.getText().toString().equals(getString(R.string.password))) {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    if (chkBeniHatirla.isChecked()) {
                        shrdPrferencs.saveString(context, "username", etUser.getText().toString());
                    } else {
                        shrdPrferencs.saveString(context, "username", "");
                    }
                    shrdPrferencs.saveBoolean(context, "remember", chkBeniHatirla.isChecked());
                } else {
                    Toast.makeText(context, getString(R.string.loginError), Toast.LENGTH_SHORT).show();
                }
            }
        }); */


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ForgotActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Login(final String etUser, final String etPass) {

        loading.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();

                                    Toast.makeText(LoginActivity.this,
                                            "Giris Basarili. \nPersonel:"
                                                    + name + "\nEmail:"
                                                    + email, Toast.LENGTH_SHORT)
                                            .show();
                                    Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("email",email);
                                    startActivity(intent);


                                    loading.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            login.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        login.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Email", etUser);
                params.put("Password", etPass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }}