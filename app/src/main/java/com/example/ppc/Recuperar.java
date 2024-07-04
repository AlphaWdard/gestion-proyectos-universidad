package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class Recuperar extends AppCompatActivity {
    TextView correo, Palabra, password, Rpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        correo = (TextView) findViewById(R.id.txtCorreoRecuperar);
        Palabra = (TextView) findViewById(R.id.txtPalabraRecuperar);
        password = (TextView) findViewById(R.id.txtContraseñaRecuperar);
        Rpassword = (TextView) findViewById(R.id.txtContraseñaConfirmar);
    }

    public void actualizar(View v){
        if (correo.getText().toString().isEmpty()){
            correo.setHint("Debe digitar el correo");
        }else if(Palabra.getText().toString().isEmpty()){
            Palabra.setHint("Debe digitar la palabra de seguridad");
        }else if(password.getText().toString().isEmpty()){
            password.setHint("Debe Digitar la contraseña");
        }else if(Rpassword.getText().toString().isEmpty()){
            Rpassword.setHint("Debe digitar la contraseña otra vez");
        }else if(!password.getText().toString().equals(Rpassword.getText().toString())){
            Snackbar contra = Snackbar.make(v, "Contraseña no coincide", Snackbar.LENGTH_SHORT);
            contra.show();
        }else {
            String URL = "http://192.168.20.38:8080/login/verificar.php";
            StringRequest StringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res", response);
                    if (response.equals("Correcto")) {
                        String URLs = "http://192.168.20.38:8080/login/cambio.php";
                        StringRequest StringRequest = new StringRequest(Request.Method.POST, URLs, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("res", response);
                                if (response.equals("Exitoso")) {
                                    Snackbar amenaza = Snackbar.make(v, response, Snackbar.LENGTH_SHORT);
                                    amenaza.show();
                                } else {
                                    Toast.makeText(Recuperar.this, "Ingreso Invalido", Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Recuperar.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> data = new HashMap<>();
                                data.put("email", correo.getText().toString());
                                data.put("palabra", Palabra.getText().toString());
                                data.put("password", password.getText().toString());
                                return data;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(StringRequest);
                    } else {
                        Toast.makeText(Recuperar.this, "Ingreso Invalido", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Recuperar.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", correo.getText().toString());
                    data.put("palabra", Palabra.getText().toString());
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(StringRequest);
        }
    }

    public void Salir(View v){
        finish();
    }
}