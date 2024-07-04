package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditarProfesor extends AppCompatActivity {
    TextView id, nombre, Titulo;
    Button salir, actualizar;
    String URL, identificacion;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_profesor);

        id = (TextView) findViewById(R.id.txtIdEditar);
        nombre = (TextView) findViewById(R.id.txtNombreEditar);
        Titulo = (TextView) findViewById(R.id.txtTituloEditar);

        salir = (Button) findViewById(R.id.btnAtrasEditar);
        actualizar = (Button) findViewById(R.id.btnEditarProfesor);
        requestQueue = Volley.newRequestQueue(EditarProfesor.this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            identificacion=bundle.getString("id");
        }

        salir.setOnClickListener(this::onClick);
        actualizar.setOnClickListener(this::onClick);
        id.setEnabled(false);

        llenar();
    }

    public void onClick(View v){
        int id = v.getId();
        if(id==R.id.btnEditarProfesor){
            Actualizar();
        }
        if(id==R.id.btnAtrasEditar){
            finish();
        }
    }

    public void llenar(){
        URL = "http://192.168.20.38:8080/login/MostrarUnProfesor.php?id="+identificacion;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            id.setText(response.getString("id"));
                            nombre.setText(response.getString("nombre"));
                            Titulo.setText(response.getString("Titulo"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarProfesor.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void Actualizar(){
        URL = "http://192.168.20.38:8080/login/ActualizarProfesor.php";
        StringRequest StringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarProfesor.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id", id.getText().toString().trim());
                data.put("nombre", nombre.getText().toString().trim());
                data.put("titulo", Titulo.getText().toString().trim());
                return data;
            }
        };

        requestQueue.add(StringRequest);
    }
}