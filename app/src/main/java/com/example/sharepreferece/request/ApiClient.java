package com.example.sharepreferece.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sharepreferece.model.Usuario;

public class ApiClient {

    private static SharedPreferences preferences;

    public static SharedPreferences conectar(Context context){
        if(preferences == null){
            preferences = context.getSharedPreferences("datos",0);
        }
        return preferences;
    }

    public static void guardar(Context context, Usuario usuario){

        SharedPreferences preferences = conectar(context);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong("dni",usuario.getDni());
        editor.putString("nombre",usuario.getNombre());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("email",usuario.getEmail());
        editor.putString("password",usuario.getPassword());
        editor.commit();
    }

    public static  Usuario leer(Context context){

        SharedPreferences preferences = conectar(context);
        long dni = preferences.getLong("dni",-1);
        String nombre = preferences.getString("nombre","-1");
        String apellido = preferences.getString("apellido","-1");
        String email = preferences.getString("email","-1");
        String password = preferences.getString("password","-1");

        Usuario user = new Usuario (dni,nombre,apellido,email,password);

        return user;
    }

    public static  Usuario login(Context context , String userName, String pass){

        Usuario user= null;
        SharedPreferences preferences = conectar(context);

        long dni = preferences.getLong("dni",-1);
        String nombre = preferences.getString("nombre","-1");
        String apellido = preferences.getString("apellido","-1");
        String email = preferences.getString("email","-1");
        String password = preferences.getString("password","-1");

        if (email.equals(userName) && password.equals(pass)){
            user = new Usuario(dni,nombre,apellido,email,password);
        }

        return user;
    }
}
