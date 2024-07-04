package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mienbros extends AppCompatActivity {
    RequestQueue requestQueue;
    String cod, codigoBusqueda, URL="";
    ListView lista;
    int tip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mienbros);

        lista = (ListView) findViewById(R.id.ListaMiembros);

        requestQueue = Volley.newRequestQueue(Mienbros.this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            cod=bundle.getString("codigo");
            tip=Integer.parseInt(bundle.getString("tipo"));
        }

        if(tip==0){
            URL = "http://192.168.20.38:8080/login/MiembrosProyecto.php";
        }else{
            URL = "http://192.168.20.38:8080/login/MiembrosGrupo.php";
        }

        llenar();
    }

    public void llenar(){
        URL += "?codigo="+codigoBusqueda;

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

                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(Mienbros.this, android.R.layout.simple_list_item_1, listraasdasd);
                            lista.setAdapter(itemsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Mienbros.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);

    }
}