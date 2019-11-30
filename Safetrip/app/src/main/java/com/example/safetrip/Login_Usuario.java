package com.example.safetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Login_Usuario extends AppCompatActivity implements View.OnClickListener {
    EditText usuario,contra;
    Button inicia,registar;
    String user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        usuario=findViewById(R.id.edtUsuario);
        contra=findViewById(R.id.edtPassword);
        inicia=findViewById(R.id.btnLogin);
        registar=findViewById(R.id.btregistro);

        inicia.setOnClickListener(this);
        registar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (inicia.isPressed()){
            user=usuario.getText().toString();
            password=contra.getText().toString();
            if (!user.isEmpty() && !password.isEmpty()){
                validarusuario("https://safetaxi.webcindario.com/validar_usuario.php");
            }else{
                Toast.makeText(Login_Usuario.this, "No se permite campos vacios", Toast.LENGTH_SHORT).show();

            }


        }
        if (registar.isPressed()){
            Intent regus = new Intent(Login_Usuario.this,Registro_usuario.class);
            startActivity(regus);
            finish();

        }
    }
    private void validarusuario(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()) {
                            Intent intent= new Intent(getApplicationContext(),Inicio_usuario.class);
                            intent.putExtra("datosusuario",response.toString());

                           // Toast.makeText(Login_Usuario.this, response.toString(),Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login_Usuario.this, "Usuario y contraseña son incorrecta", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login_Usuario.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("correo",usuario.getText().toString());
                parametros.put("contra",contra.getText().toString());
                return parametros;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
