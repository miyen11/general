package com.example.jazch.generalapp.dateBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class abmcSql {

    SQLiteDatabase sqLiteDatabase;
    sqlDataBase sqlDataBase;
    public abmcSql(sqlDataBase sqlDataBase) {
        this.sqlDataBase = sqlDataBase;
        this.sqLiteDatabase = sqlDataBase.getWritableDatabase();
    }


    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public sqlDataBase getSqlDataBase() {
        return sqlDataBase;
    }

    public void setSqlDataBase(sqlDataBase sqlDataBase) {
        this.sqlDataBase = sqlDataBase;
    }

    public void insertUser(String name, String password){
        if(getSqLiteDatabase()!= null){
            getSqLiteDatabase().execSQL("INSERT INTO user (name, password)VALUES('"+name+"','"+password+"')");
        }
    }

    public int existUser(String name, String password){
        int cant = 0;
        String consult = "SELECT COUNT(name) FROM user WHERE name='"+name+"'AND password='"+password+"' ";
        Cursor cursor = getSqLiteDatabase().rawQuery(consult, null);
        if(cursor.moveToFirst()) {
            do {
                cant = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        return cant;
    }

    public ArrayList<user> getUsers(){
        String consult = "SELECT * FROM user";
        ArrayList<user> arrayUsers = new ArrayList<user>();
        Cursor  cursor = getSqLiteDatabase().rawQuery(consult,null);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                String password = cursor.getString(1);
                user user = new user(name,password);
                arrayUsers.add(user);
            }while (cursor.moveToNext());
        }
        return arrayUsers;
    }

    public void deleteUsers(){
        String consult = "DELETE FROM user";
        if(getSqLiteDatabase()!=null){
            getSqLiteDatabase().execSQL(consult);
        }
    }

    public ArrayList<user> getUser(String name){
        String consult ="SELECT * FROM user WHERE name='"+name+"'";
        Cursor cursor = getSqLiteDatabase().rawQuery(consult,null);
        ArrayList<user> userArrayList = new ArrayList<user>();
        if(cursor.moveToFirst()){
            do{
                String nameUser = cursor.getString(0);
                String passwordUser = cursor.getString(1);
                user user = new user(nameUser,passwordUser);
                userArrayList.add(user);
            }while (cursor.moveToNext());
        }
        return userArrayList;
    }

    public String deleteUser(String name) {
        String result = "";
        String consult = "DELETE FROM user where name= '"+name+"'";
        if(getSqLiteDatabase()!=null){
            if(getUser(name).size()==0){
                result = "no exsiste ningun registro con el nombre"+name;
            }else {
                getSqLiteDatabase().execSQL(consult);
                if(getUser(name).size()==0){
                    result ="el registro con el name: "+name+" se elimino correctamente";
                }else {
                    result ="no se pudo eliminar el registro con el name: "+name;
                }
            }

        }
        return result;
    }

    public String updateUser(String name,String password){
        String result = "";
        String consult ="UPDATE user SET password='"+password+"' WHERE name='"+name+"' ";
        if (getSqLiteDatabase()!=null) {
            if(getUser(name).size()==0){
                result ="no existe ningun registro con el nombre "+name;
            }else {
                getSqLiteDatabase().execSQL(consult);
                result="se modifico el registro con el nombre: "+name;
            }
        }
        return result;
    }

}

