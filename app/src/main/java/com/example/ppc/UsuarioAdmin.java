package com.example.ppc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UsuarioAdmin extends AppCompatActivity {

    private String nombre, password;
    private TextView usuario;
    private Button profesores, buscarProfe, salir, grupos, proyectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_admin);

        usuario = (TextView) findViewById(R.id.lblAdmin);
        profesores= (Button) findViewById(R.id.btnConsultarProfesores);
        buscarProfe = (Button) findViewById(R.id.btnEditarProfesores);
        grupos = (Button) findViewById(R.id.btnAgregarGrupo);
        proyectos = (Button) findViewById(R.id.btnAgregarProyecto);
        salir = (Button) findViewById(R.id.btnClose);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            nombre=bundle.getString("Nombre");
            password=bundle.getString("password");
        }

        usuario.setText(nombre);
        profesores.setOnClickListener(this::onClick);
        buscarProfe.setOnClickListener(this::onClick);
        salir.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        int id=v.getId();
        if(id == R.id.btnConsultarProfesores){
            profes();
        }
        if(id==R.id.btnEditarProfesores){
            busca();
        }
        if(id==R.id.btnClose){
            Intent intent = new Intent(UsuarioAdmin.this, Login.class);
            startActivity(intent);
            finish();
        }
        if(id==R.id.btnAgregarGrupo){
            grupo();
        }
        if(id==R.id.btnAgregarProyecto){
            proyecto();
        }
    }

    public void profes(){
        Intent intent = new Intent(UsuarioAdmin.this, Profesores.class);
        startActivity(intent);
    }

    public void busca(){
        Intent intent = new Intent(UsuarioAdmin.this, BuscarProfesor.class);
        startActivity(intent);
    }

    public void grupo(){
        Intent intent = new Intent(UsuarioAdmin.this, AgregarGrupo.class);
        startActivity(intent);
    }

    public void proyecto(){
        Intent intent = new Intent(UsuarioAdmin.this, AgregarProyecto.class);
        startActivity(intent);
    }
}