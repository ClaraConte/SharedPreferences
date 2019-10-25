package com.example.sharepreferece.ui.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.sharepreferece.model.Usuario;
import com.example.sharepreferece.request.ApiClient;

public class ViewModelMain extends ViewModel{

    // pegar en gradel   implementation 'androidx.lifecycle:lifecycle-extensions:2.1.0'

    private MutableLiveData<Usuario> mldUsuario;

    public LiveData<Usuario> getMLDUsuario(){

        if(mldUsuario == null){
            mldUsuario = new MutableLiveData<>();
        }
        return mldUsuario;
    }

    public boolean loginUsuario(Context context, String email, String pass){

        Usuario user = ApiClient.login(context, email, pass);
        mldUsuario.setValue(user);
        if(user == null){

            return false;
        }
        return true;
    }


}
