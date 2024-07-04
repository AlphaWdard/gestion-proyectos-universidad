package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profesores extends AppCompatActivity {
    String URL;
    ListView lista;
    Button Salir;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        requestQueue = Volley.newRequestQueue(Profesores.this);
        profesores();

        lista = (ListView) findViewById(R.id.ListaProfesores);
        Salir = (Button) findViewById(R.id.btnAtrasProfesores);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] texto=lista.getItemAtPosition(position).toString().split("-");

                Intent intent = new Intent(Profesores.this, Profesor.class);
                intent.putExtra("id", texto[0]);
                startActivity(intent);
            }
        });
    }

    public void onClick(View view){
        finish();
    }

    private void profesores(){
        URL = "http://192.168.20.38:8080/login/MostrarProfesor.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            String[] listaProfes = new String[response.length()];
                            for (int i=0; i<response.length(); i++ ){
                                JSONObject profesores = response.getJSONObject(i);

                                listaProfes[i] = Integer.parseInt(profesores.getString("id"))+"-"+profesores.getString("nombre");
                            }

                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(Profesores.this, android.R.layout.simple_list_item_1, listaProfes);
                            lista.setAdapter(itemsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profesores.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
}