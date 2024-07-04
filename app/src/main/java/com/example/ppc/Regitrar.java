package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Regitrar extends AppCompatActivity {
    TextView id, nombre, titulo, correo, palabra, contraseña, Rcontraseña;
    String URL;
    RequestQueue requestQueue;
    Boolean Verifica=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitrar);

        id =(TextView) findViewById(R.id.txtIdRegistro);
        titulo =(TextView) findViewById(R.id.txtTituloRegistro);
        nombre =(TextView) findViewById(R.id.txtNombreRegistro);
        correo =(TextView) findViewById(R.id.txtCorreoRegistro);
        palabra =(TextView) findViewById(R.id.txtPalabraRegistro);
        contraseña =(TextView) findViewById(R.id.txtContraseñaRegistro);
        Rcontraseña =(TextView) findViewById(R.id.txtConfirmarContraseña);

        requestQueue = Volley.newRequestQueue(Regitrar.this);
    }

    public void registrar(View v){
        verificarcorreo();
        verificarid();
        if(id.getText().toString().isEmpty()){
            id.setHint("Debe Digitar el id del Profesor");
        }else if(titulo.getText().toString().isEmpty()){
            titulo.setHint("Debe digitar el titulo");
        }else if(nombre.getText().toString().isEmpty()){
            nombre.setHint("Debe digitar el nombre");
        }else if (correo.getText().toString().isEmpty()){
            correo.setHint("Debe digitar el correo");
        }else if(palabra.getText().toString().isEmpty()){
            palabra.setHint("Debe digitar la palabra de seguridad");
        }else if(contraseña.getText().toString().isEmpty()){
            contraseña.setHint("Debe Digitar la contraseña");
        }else if(Rcontraseña.getText().toString().isEmpty()){
            Rcontraseña.setHint("Debe digitar la contraseña otra vez");
        }else if(Verifica==true){
            Snackbar mySnackbar = Snackbar.make(v, "Usuario existente", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
            Verifica=false;
        }else if(!contraseña.getText().toString().equals(Rcontraseña.getText().toString())){
            Snackbar contra = Snackbar.make(v, "Contraseña no coincide", Snackbar.LENGTH_SHORT);
            contra.show();
        }else{
            URL = "http://192.168.20.38:8080/login/register.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Snackbar amenaza = Snackbar.make(v, response, Snackbar.LENGTH_SHORT);
                            amenaza.show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Regitrar.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("id", id.getText().toString().trim());
                    data.put("nombre", nombre.getText().toString().trim());
                    data.put("titulo", titulo.getText().toString().trim());
                    data.put("email", correo.getText().toString().trim());
                    data.put("password", contraseña.getText().toString().trim());
                    data.put("palabra", palabra.getText().toString().trim());
                    return data;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    public void verificarid(){
        URL = "http://192.168.20.38:8080/login/MostarUnUsuario.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals(id.getText().toString())){
                            Verifica = true;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Regitrar.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id", id.getText().toString().trim());
                return data;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void verificarcorreo(){
        URL = "http://192.168.20.38:8080/login/MostrarUnUsuarioCorreo.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals(correo.getText().toString())){
                            Verifica = true;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Regitrar.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("correo", correo.getText().toString().trim());
                return data;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void salir(View v){
        finish();
    }
}