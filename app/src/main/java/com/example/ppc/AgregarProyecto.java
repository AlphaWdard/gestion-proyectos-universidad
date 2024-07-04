package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.FileObserver;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AgregarProyecto extends AppCompatActivity {
    TextView Nombre, Codigo;
    Spinner Areas;
    ArrayList<String> lista;
    RequestQueue requestQueue;
    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_proyecto);

        Nombre = (TextView) findViewById(R.id.txtNombreProyectoRegistro);
        Codigo = (TextView) findViewById(R.id.txtCodigoProyectoRegistro);
        Areas = (Spinner) findViewById(R.id.SpinnerArea);

        requestQueue = Volley.newRequestQueue(AgregarProyecto.this);

        llenarAreas();

        ArrayAdapter adapter = new ArrayAdapter(AgregarProyecto.this, android.R.layout.simple_spinner_dropdown_item, lista);

        Areas.setAdapter(adapter);
    }

    public void llenarAreas(){
        lista = new ArrayList<>();
        URL = "http://192.168.20.38:8080/login/Areas.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            String[] listraasdasd = new String[response.length()];
                            for (int i=0; i<response.length(); i++ ){
                                JSONObject proyecto = response.getJSONObject(i);

                                lista.add(Integer.parseInt(proyecto.getString("codigo"))+"-"+proyecto.getString("nombre"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AgregarProyecto.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void Registrar(View v) {
        if (Codigo.getText().toString().isEmpty()) {
            Codigo.setHint("Debe Digitar codigo del Proyecto");
        } else if (Nombre.getText().toString().isEmpty()) {
            Nombre.setHint("Debe digitar el nombre");
        } else if (Areas.isSelected()) {
            Snackbar mySnackbar = Snackbar.make(v, "Seleccione un Area", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        } else {
            URL = "http://192.168.20.38:8080/login/registerGrupo.php";
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
                    Toast.makeText(AgregarProyecto.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("codigo", Codigo.getText().toString().trim());
                    data.put("nombre", Nombre.getText().toString().trim());
                    data.put("area", String.valueOf(Areas.getSelectedItemPosition()));
                    return data;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    public void Cerrar(View v){ finish(); }
}