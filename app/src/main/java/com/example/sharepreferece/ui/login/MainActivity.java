package com.example.sharepreferece.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharepreferece.R;
import com.example.sharepreferece.model.Usuario;
import com.example.sharepreferece.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    private EditText user, password;
    private Button login, register;
    private ViewModelMain viewModelMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icicializar();
    }

    private void icicializar(){

        viewModelMain = ViewModelProviders.of(this).get(ViewModelMain.class);

        user = findViewById(R.id.etUsuario);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(viewModelMain.loginUsuario(getApplicationContext(), user.getText()+"",password.getText()+"")){
                   Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                   intent.putExtra("editar", "1");
                   startActivity(intent);
               }else {
                   Toast.makeText(getApplicationContext(),"datos incorrectos",Toast.LENGTH_LONG).show();
               }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                intent.putExtra("editar", "0");
                startActivity(intent);
            }
        });

        final Observer<Usuario> userObserver = new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

            }
        };

        viewModelMain.getMLDUsuario().observe(this, userObserver);
    }


}
