package com.example.sharepreferece.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sharepreferece.R;
import com.example.sharepreferece.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText dni, nombre, apellido, email, password;
    private Button  register;
    private ViewModelRegistro viewModelRegistro;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        icicializar();
    }

    private void icicializar() {

        viewModelRegistro = ViewModelProviders.of(this).get(ViewModelRegistro.class);

        dni = findViewById(R.id.etDni);
        nombre = findViewById(R.id.etNombre);
        apellido = findViewById(R.id.etApellido);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPass);
        register = findViewById(R.id.btGuardar);

        final Observer<Usuario> observer = new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if(usuario != null) {
                    dni.setText(usuario.getDni()+"");
                    nombre.setText(usuario.getNombre());
                    apellido.setText(usuario.getApellido());
                    email.setText(usuario.getEmail());
                    password.setText(usuario.getPassword());
                }
            }
        };

        viewModelRegistro.getMLDUsuario().observe(this, observer);

        Intent i = getIntent();
        String dato = i.getStringExtra("editar");

        if(dato.equals("1")){
            viewModelRegistro.leerDatos(getApplicationContext());
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setDni(Long.parseLong(dni.getText().toString()));
                usuario.setNombre(nombre.getText().toString());
                usuario.setApellido(apellido.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setPassword(password.getText().toString());
                viewModelRegistro.cargarDatos(getApplicationContext(),usuario);
                Toast.makeText(getApplicationContext(),"Datos guardados",Toast.LENGTH_LONG).show();
            }
        });

    }

}
