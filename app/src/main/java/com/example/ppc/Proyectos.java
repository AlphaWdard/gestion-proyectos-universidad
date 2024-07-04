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

public class Proyectos extends AppCompatActivity {
    String URL, nombre, password;
    ListView lista;
    Button Salir;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyectos);

        requestQueue = Volley.newRequestQueue(Proyectos.this);
        proyectos();

        lista = (ListView) findViewById(R.id.ListaProyectos);
        Salir = (Button) findViewById(R.id.btnSalir);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            nombre=bundle.getString("Nombre");
            password=bundle.getString("password");
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] texto=lista.getItemAtPosition(position).toString().split("-");

                Intent intent = new Intent(Proyectos.this, UnirseProyecto.class);
                intent.putExtra("codigo", texto[0]);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }

    public void onClick(View view){
        finish();
    }

    private void proyectos(){
        URL = "http://192.168.20.38:8080/login/MostrarProyecto.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            String[] listraasdasd = new String[response.length()];
                            for (int i=0; i<response.length(); i++ ){
                                JSONObject proyecto = response.getJSONObject(i);

                                listraasdasd[i] = Integer.parseInt(proyecto.getString("codigo"))+"-"+proyecto.getString("nombre");
                            }

                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(Proyectos.this, android.R.layout.simple_list_item_1, listraasdasd);
                            lista.setAdapter(itemsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Proyectos.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
}