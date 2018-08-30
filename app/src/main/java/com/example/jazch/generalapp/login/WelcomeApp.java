package com.example.jazch.generalapp.login;
;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jazch.generalapp.R;
import com.example.jazch.generalapp.dateBase.abmcSql;
import com.example.jazch.generalapp.dateBase.sqlDataBase;
import com.example.jazch.generalapp.dateBase.user;
import com.example.jazch.generalapp.menu.menuLateral;

import java.security.interfaces.DSAKey;
import java.util.ArrayList;

public class WelcomeApp extends AppCompatActivity implements View.OnClickListener{

    TextView textViewName,textViewPassword;
    EditText editTextPassword, editTextName;
    Button btnAdd,btnMostrar,btnEliminar,btnMostrarUno,btnEliminarUno,btnUpdate,btnLogin;
    private  abmcSql abmcSql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_app);
        initializateIU();
        createDataBase();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void createDataBase(){
        sqlDataBase sqlDataBase = new sqlDataBase(this, "dbLocal",null,1);
        abmcSql = new abmcSql(sqlDataBase);
    }

    private void initializateIU(){
        textViewName= findViewById(R.id.textViewName);
        textViewPassword = findViewById(R.id.textViewPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnAdd= findViewById(R.id.btnAdd);
        btnMostrar= findViewById(R.id.btnMostrar);
        btnEliminar=findViewById(R.id.btnEliminar);
        btnMostrarUno = findViewById(R.id.btnMostrarUno);
        btnEliminarUno= findViewById(R.id.btnEliminarUno);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnLogin=findViewById(R.id.btnLogin);
        btnAdd.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrarUno.setOnClickListener(this);
        btnEliminarUno.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addUser();
                break;
            case R.id.btnMostrar:
                getUsers();
                break;
            case R.id.btnEliminar:
                deleteUsers();
                break;
            case R.id.btnMostrarUno:
                getUser();
                break;
            case R.id.btnEliminarUno:
                deleteUser();
                break;
            case R.id.btnUpdate:
                updateUser();
                break;
            case R.id.btnLogin:
                logIn();
                break;
        }
    }

    private void logIn(){
        String name = String.valueOf(editTextName.getText());
        String password = String.valueOf(editTextPassword.getText());
        if(name.equals("")|| password.equals("")){
            Toast.makeText(this, "ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
        }else {
            if (getAbmcSql().existUser(name,password) == 1){
                Intent intent = new Intent(this,menuLateral.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Usuario y/o constraseña inconrrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void updateUser(){
        String name = String.valueOf(editTextName.getText());
        String password = String.valueOf(editTextPassword.getText());
        if(name.equals("") || password.equals("")){
            Toast.makeText(this, "ingrese un nombre y contraseña", Toast.LENGTH_SHORT).show();
        }else {

            Toast.makeText(this, ""+getAbmcSql().updateUser(name,password), Toast.LENGTH_SHORT).show();
        }
    }

    private void getUser(){
        String name = String.valueOf(editTextName.getText());
        String listUser ="";
        if(name.equals("")){
            Toast.makeText(this, "ingrese un nombre para la busqueda", Toast.LENGTH_SHORT).show();
        }else {
            ArrayList<user > userArrayList = getAbmcSql().getUser(name);
            if(userArrayList.size()== 0){
                Toast.makeText(this, "no existe ningun usuario con el nombre: "+name, Toast.LENGTH_SHORT).show();
            }else {
                for (int i = 0; i< userArrayList.size();i++){
                    listUser+="name: "+userArrayList.get(i).getName()+" password: "+userArrayList.get(i).getPassword();
                }
                Toast.makeText(this, ""+listUser, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void addUser(){
        String name = String.valueOf(editTextName.getText()) ;
        String password = String.valueOf(editTextPassword.getText());
        getAbmcSql().insertUser(name, password);
    }

    private void getUsers(){
        if(getAbmcSql()!=null){
            ArrayList<user> users = getAbmcSql().getUsers();
            String list = "";
            for(int i = 0; i< users.size();i++){
                list+="nombre: "+users.get(i).getName()+" password"+users.get(i).getPassword()+"/n";
            }
            Toast.makeText(this, ""+list, Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteUsers() {
        getAbmcSql().deleteUsers();
    }

    private void deleteUser(){
        String name = String.valueOf(editTextName.getText());
        if (name.equals("")){
            Toast.makeText(this, "ingrese un nombre para realizar la eliminarcion", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, ""+getAbmcSql().deleteUser(name), Toast.LENGTH_SHORT).show();
        }
    }

    public abmcSql getAbmcSql() {
        return abmcSql;
    }

    public void setAbmcSql(abmcSql abmcSql) {
        this.abmcSql = abmcSql;
    }
}
