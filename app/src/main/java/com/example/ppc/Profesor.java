package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profesor extends AppCompatActivity {
    TextView codigo, nombre, titulo;
    Button atras, editar;
    String URL, cod;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);

        codigo = (TextView) findViewById(R.id.txtIdProfesor);
        nombre = (TextView) findViewById(R.id.txtNombreProfesor);
        titulo = (TextView) findViewById(R.id.txtTituloProfesor);
        atras = (Button) findViewById(R.id.btnAtras);
        editar = (Button) findViewById(R.id.btnEditar);

        requestQueue = Volley.newRequestQueue(Profesor.this);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            cod=bundle.getString("id");
        }

        llenar();
    }

    public void llenar(){
        URL = "http://192.168.20.38:8080/login/MostrarUnProfesor.php?id="+cod;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            codigo.setText(response.getString("id"));
                            nombre.setText(response.getString("nombre"));
                            titulo.setText(response.getString("Titulo"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profesor.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }


    public void salir(View v){
        finish();
    }

    public void editare(View v){
        Intent intent = new Intent(Profesor.this, EditarProfesor.class);
        intent.putExtra("id", codigo.getText());
        startActivity(intent);
        finish();
    }
}