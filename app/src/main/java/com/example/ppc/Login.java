package com.example.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText txtId, txtContra;
    private String email, contraseña;
    private String URL="http://192.168.20.38:8080/login/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=contraseña = "";
        txtId = findViewById(R.id.txtIdLogin);
        txtContra = findViewById(R.id.txtContraLogin);
    }

    public void login(View View) {
        email = txtId.getText().toString().trim();
        contraseña = txtContra.getText().toString().trim();

        if(!email.equals("") && !contraseña.equals("")){
             StringRequest StringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                     Log.d("res", response);
                     if (response.equals("Exitoso")) {
                         if(email.equals("admin")){
                             Intent intent = new Intent(Login.this, UsuarioAdmin.class);
                             intent.putExtra("Nombre", email);
                             intent.putExtra("password", contraseña);
                             startActivity(intent);
                             finish();
                         } else {
                             Intent intent = new Intent(Login.this, UsuarioProfesor.class);
                             intent.putExtra("Nombre", email);
                             intent.putExtra("password", contraseña);
                             startActivity(intent);
                             finish();
                         }
                     } else if (response.equals("failure")) {
                         Toast.makeText(Login.this, "Ingreso Invalido", Toast.LENGTH_LONG).show();
                     }
                 }
             }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                 }
             }){
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map<String, String> data = new HashMap<>();
                     data.put("email", email);
                     data.put("password", contraseña);
                     return data;
                 }
             };
             RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
             requestQueue.add(StringRequest);
         } else{
             Toast.makeText(this, "Datos de ingreso incompletos!", Toast.LENGTH_SHORT).show();
         }
    }

    public void Registrar(View v){
        Intent intent = new Intent(Login.this, Regitrar.class);
        startActivity(intent);
    }

    public void olvidos(View v){
        Intent intent = new Intent(Login.this, Recuperar.class);
        startActivity(intent);
    }
}