package com.example.sharepreferece.ui.registro;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sharepreferece.model.Usuario;
import com.example.sharepreferece.request.ApiClient;

public class ViewModelRegistro extends ViewModel {

    private MutableLiveData<Usuario> mldUsuario;

    public LiveData<Usuario> getMLDUsuario(){

        if(mldUsuario == null){
            mldUsuario = new MutableLiveData<>();
        }
        return mldUsuario;
    }

    public void cargarDatos(Context context, Usuario user){

        ApiClient.guardar(context, user);
        mldUsuario.setValue(user);
    }

    public  Usuario leerDatos(Context context){
        mldUsuario.setValue(ApiClient.leer(context));
        return ApiClient.leer(context);

    }

}
