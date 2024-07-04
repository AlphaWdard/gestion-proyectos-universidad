package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UnirseGrupo extends AppCompatActivity {
    TextView codigo, nombre, area, dato;
    Button atras, unir;
    String URL, cod, usuario, password, codigoprofe="";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_grupo);

        codigo = (TextView) findViewById(R.id.txtIdGrupo);
        nombre = (TextView) findViewById(R.id.txtNombreGrupo);
        area = (TextView) findViewById(R.id.txtAreaGrupo);
        atras = (Button) findViewById(R.id.btnSalir);
        unir = (Button) findViewById(R.id.btnUnirseGrupo);

        requestQueue = Volley.newRequestQueue(UnirseGrupo.this);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            cod=bundle.getString("codigo");
            usuario=bundle.getString("Nombre");
            password=bundle.getString("password");
        }

        llenar();
    }

    public void llenar(){
        URL = "http://192.168.20.38:8080/login/MostrarUnGrupo.php?codigo="+cod;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            codigo.setText(response.getString("codigo"));
                            nombre.setText(response.getString("nombre"));
                            area.setText(response.getString("area"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UnirseGrupo.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonObjectRequest);

    }




    public void buscar(View v){
        URL = "http://192.168.20.38:8080/login/Usuario.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String URLs= "http://192.168.20.38:8080/login/UniserGrupo.php";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String responseS) {

                                        Toast.makeText(getApplicationContext(), "Se Registro el Profesor en el Grupo", Toast.LENGTH_SHORT).show();
                                        finish();

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(UnirseGrupo.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        ){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> data = new HashMap<>();
                                data.put("codigo", (String) codigo.getText());
                                data.put("codigoProfesor", response);
                                return data;
                            }
                        };
                        requestQueue.add(stringRequest);
                        dato.setText(codigoprofe);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UnirseGrupo.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", usuario.trim());
                data.put("password", password.trim());
                return data;
            }
        };
        requestQueue.add(stringRequest);

    }


    public void miembros(View v){
        Intent intent = new Intent(UnirseGrupo.this, Regitrar.class);
        intent.putExtra("codigo",(String) codigo.getText());
        intent.putExtra("Tipo", 1);
        startActivity(intent);
    }

    public void salir(View v){
        finish();
    }
}