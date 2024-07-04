package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UsuarioProfesor extends AppCompatActivity {
    private String nombre, password;
    private TextView usuario;
    private Button Grupos, proyectos, salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_profesor);

        usuario = (TextView) findViewById(R.id.lblUsuario);
        Grupos = (Button) findViewById(R.id.btnGrupos);
        proyectos = (Button) findViewById(R.id.btnProyectos);
        salir = (Button) findViewById(R.id.btnSalir);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            nombre=bundle.getString("Nombre");
            password=bundle.getString("password");
        }

        usuario.setText(nombre);
        Grupos.setOnClickListener(this::onClick);
        proyectos.setOnClickListener(this::onClick);
        salir.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        int id=v.getId();
        if(id == R.id.btnGrupos){
            grupos();
        }
        if(id==R.id.btnProyectos){
            proyectos();
        }
        if(id==R.id.btnSalir){
            Intent intent = new Intent(UsuarioProfesor.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    public void grupos(){
        Intent intent = new Intent(UsuarioProfesor.this, GruposInvestigacion.class);
        intent.putExtra("Nombre", nombre);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    public void proyectos(){
        Intent intent = new Intent(UsuarioProfesor.this, Proyectos.class);
        intent.putExtra("Nombre", nombre);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}