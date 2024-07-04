package com.example.ppc;

import static java.time.LocalDate.now;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AgregarGrupo extends AppCompatActivity {
    TextView Nombre, Codigo, Presupuesto;
    RequestQueue requestQueue;
    String URL;
    LocalDate date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_proyecto);

        Nombre = (TextView) findViewById(R.id.txtNombreGrupoRegistro);
        Codigo = (TextView) findViewById(R.id.txtCodigoGrupoRegistro);
        Presupuesto = (TextView) findViewById(R.id.txtPresupuesto);

        requestQueue = Volley.newRequestQueue(AgregarGrupo.this);

    }

    public void Registrar(View v) {
        if (Codigo.getText().toString().isEmpty()) {
            Codigo.setHint("Debe Digitar codigo del Proyecto");
        } else if (Nombre.getText().toString().isEmpty()) {
            Nombre.setHint("Debe digitar el nombre");
        } else if (Presupuesto.getText().toString().isEmpty()) {
            Presupuesto.setHint("Debe digitar el nombre");
        } else {
            URL = "http://192.168.20.38:8080/login/registerProyecto.php";
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
                    Toast.makeText(AgregarGrupo.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("codigo", Codigo.getText().toString().trim());
                    data.put("nombre", Nombre.getText().toString().trim());
                    data.put("presupuesto", Presupuesto.getText().toString().trim());
                    data.put("fecha", date.toString());
                    return data;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    public void Cerrar(View v){ finish(); }
}