package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuscarProfesor extends AppCompatActivity {
    Button Salir, Buscar;
    TextView identificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_profesor);

        identificacion = (TextView) findViewById(R.id.txtIdBuscar);
        Salir = (Button) findViewById(R.id.btnAtrasBuscar);
        Buscar = (Button) findViewById(R.id.btnBuscar);

        Salir.setOnClickListener(this::onClick);
        Buscar.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        int id = v.getId();
        if(id==R.id.btnBuscar){
            Intent intent = new Intent(BuscarProfesor.this, Profesor.class);
            intent.putExtra("id", identificacion.getText().toString());
            startActivity(intent);
        }
        if(id==R.id.btnAtrasBuscar){ finish();}
    }
}